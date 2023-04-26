package com.resume.controller;

import com.resume.dto.UserDTO;
import com.resume.dto.UserImageDTO;
import com.resume.service.ManageMentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
public class MemberManageController {
    private final ManageMentService service;

    public MemberManageController(ManageMentService service) {
        this.service = service;
    }

    @GetMapping("/profile/{userId}")
    public String main(@PathVariable String userId,@SessionAttribute("userSession")String userSession, Model model){
        model.addAttribute("userId",userSession);
        return "management/mainForm";
    }
    @PostMapping("/profile/{userId}")
    public String user(@PathVariable("userId") String userId,Model model,@SessionAttribute("userSession")
            String sessionId){
        model.addAttribute("user",service.findById(userId,sessionId));
        return "management/modifyForm";
    }
    @PostMapping("/profile/checkpw")
    @ResponseBody
    public Integer checkPw(@Validated @RequestBody UserDTO user, BindingResult bindingResult,@SessionAttribute("userSession")
                           String userId){
        if (bindingResult.hasErrors()){
            return 200;
        }
        return service.checkPw(
                UserDTO.builder().
                        password(user.getPassword())
                        .userid(userId)
                        .build());
    }
    @PostMapping("/profile/{userid}/modify")
    public String userImageUpdate(@RequestParam MultipartFile file,Model model) throws IOException {
        log.info("uploadFile={}",file);

        File newFileName = new File(file.getOriginalFilename());
        file.transferTo(newFileName);
        return "management/modifyForm";
    }



}
