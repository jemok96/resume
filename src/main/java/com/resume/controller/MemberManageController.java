package com.resume.controller;

import com.resume.dto.UserDTO;
import com.resume.dto.AwsS3;
import com.resume.dto.UserPwDTO;
import com.resume.service.ManageMentService;
import com.resume.service.S3UploadService;
import com.resume.service.UserImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@Slf4j
public class MemberManageController {
    private final ManageMentService service;
    private final S3UploadService s3Upload;
    private final UserImageService imageService;

    public MemberManageController(ManageMentService service, S3UploadService s3Upload, UserImageService imageService) {
        this.service = service;
        this.s3Upload = s3Upload;
        this.imageService = imageService;
    }

    @GetMapping("/profile/{userId}")
    public String main(@PathVariable String userId,@SessionAttribute("userSession")String userSession, Model model){
        model.addAttribute("userId",userSession);
        return "management/mainForm";
    }
    @PostMapping("/profile/{userId}")
    public String user(@PathVariable("userId") String userId,Model model,@SessionAttribute("userSession")
            String sessionId){
        model.addAttribute("userImage",imageService.findImageById(userId));
        return "management/modifyForm";
    }
    @PostMapping("/profile/checkpw")
    @ResponseBody
    public Integer checkPw(@Validated @RequestBody UserDTO user, BindingResult bindingResult,@SessionAttribute("userSession")
                           String sessionId){
        if (bindingResult.hasErrors()){
            return 200;
        }
        boolean status =service.checkPw(
                UserDTO.builder().
                        password(user.getPassword())
                        .userid(sessionId)
                        .build());
        if(status)return 1;
        else return 300;
    }
    @PostMapping("/profile/{userid}/modify")
    public String userImageUpdate(@RequestParam MultipartFile file, Model model, RedirectAttributes attr, @PathVariable String userid) throws IOException {

        //user image table 저장하고, 수정 할 경우 aws내에서도 삭제하고 다시 저장하는 로직으로 변경해야함 (Transactional)
        AwsS3 userImage = s3Upload.upload(file, "userImage"); //
        userImage.setUserId(userid);
        imageService.updateImage(userImage);
        attr.addFlashAttribute("status","OK");
        return "redirect:/profile/{userid}";
    }
    @GetMapping("/profile/{userId}/modifypw")
    public String modifyPassword(@PathVariable("userId") String userId,@SessionAttribute("userSession")String sessionId
    ,RedirectAttributes attr){

        if(!userId.equals(sessionId)){
            attr.addFlashAttribute("status","NO");
            return "redirect:/profile/{userId}";
        }
        return "management/modifyPasswordForm";
    }

    @PostMapping("/profile/{userId}/modifypw")
    @ResponseBody
    public Integer modifyPasswordCheck(@PathVariable("userId") String userId, @SessionAttribute("userSession")String sessionId,
                                       @Validated @RequestBody UserPwDTO userPw,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return 400;
        }

        return service.changePw(userPw,sessionId);
    }

    @GetMapping("/profile/delete")
    public String userDelete(){

        return "management/deleteForm";
    }

}
