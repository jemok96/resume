package com.resume.controller;

import com.resume.dto.SelfIntroDTO;
import com.resume.service.SelfIntroService;
import com.resume.service.UserImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@Slf4j
public class SelfIntroController {

    private final SelfIntroService service;
    private final UserImageService imageService;
    public SelfIntroController(SelfIntroService service, UserImageService imageService) {
        this.service = service;
        this.imageService = imageService;
    }

    @GetMapping("/selfintroduction")
    public String main(@SessionAttribute(value = "userSession" ,required = false)String userId, Model model){
        if(userId !=null){
            model.addAttribute("resume",service.findResumeById(userId));
        }

        model.addAttribute("userImage",imageService.findImageById(userId));
        return "resume/mainForm";
    }
    @GetMapping("/selfintroduction/new")
    public String mainAddForm(Model model, @SessionAttribute("userSession")String userId){
        model.addAttribute("resume",new SelfIntroDTO());
        model.addAttribute("userImage",imageService.findImageById(userId));
        return "resume/addForm";
    }
    @PostMapping("/selfintroduction")
    public String addCheck(@Validated @ModelAttribute("resume") SelfIntroDTO dto,
                           BindingResult bindingResult,@SessionAttribute(value = "userSession" ,required = false)String userid,
                           Model model){
        model.addAttribute("userImage",imageService.findImageById(userid));
        if(bindingResult.hasErrors()){
            return "resume/addForm";
        }
        SelfIntroDTO userInput = SelfIntroDTO.builder()
                        .userid(userid)
                        .title(dto.getTitle())
                        .contents(dto.getContents()).build();
        service.insertResume(userInput);
        model.addAttribute("resume",dto);
        return "redirect:/selfintroduction";
    }
    @GetMapping("/selfintroduction/{rno}")
    public String modifyResume(@PathVariable Integer rno,Model model, @SessionAttribute(value = "userSession",required = true)String userId){
        model.addAttribute("resume",service.selectByNo(rno));
        model.addAttribute("userImage",imageService.findImageById(userId));
        return "resume/modifyForm";
    }
    @PatchMapping("/selfintroduction/{rno}")
    @ResponseBody
    public int modifyCheck(@Validated @RequestBody SelfIntroDTO dto, BindingResult bindingResult,
                           @PathVariable Integer rno, Model model, @SessionAttribute(value = "userSession",required = true)String userId,
                           RedirectAttributes attr){
        model.addAttribute("userImage",imageService.findImageById(userId));
        if(bindingResult.hasErrors()){
            return 400;
        }
        SelfIntroDTO resume = SelfIntroDTO.builder()
                        .title(dto.getTitle())
                        .contents(dto.getContents())
                        .resumeno(rno)
                        .userid(userId).build();
        return service.updateResume(resume);

    }

    @DeleteMapping("/selfintroduction/{rno}")
    @ResponseBody
    public Integer deleteResume(@PathVariable Integer rno){
        return service.deleteOne(rno);
    }
}
