package com.resume.controller;

import com.resume.dto.NoticeDTO;
import com.resume.service.UserImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
public class NoticeController {
    private final UserImageService imageService;

    public NoticeController(UserImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/notice")
    public String NoticeMain(@SessionAttribute("userSession")String userId, Model model){
        model.addAttribute("userImage",imageService.findImageById(userId));
        return "notice/mainForm";
    }
    @GetMapping("/notice/write")
    public String NoticeWritePage(@SessionAttribute("userSession")String userId,Model model){
        model.addAttribute("userImage",imageService.findImageById(userId));
        model.addAttribute("notice", new NoticeDTO());
        return "notice/addForm";
    }
    @PostMapping("/notice/write")
    public String NoticeWrite(@SessionAttribute("userSession")String userId, Model model,
                              @Validated @ModelAttribute("notice")NoticeDTO notice, BindingResult bindingResult){
        model.addAttribute("userImage",imageService.findImageById(userId));
        log.info("notice={}",notice);
        if(bindingResult.hasErrors()){
            log.info("error={}",bindingResult);
            return "notice/addForm";
        }
        return "redirect:/notice";
    }
}
