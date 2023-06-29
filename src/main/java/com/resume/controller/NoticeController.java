package com.resume.controller;

import com.resume.service.UserImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
}
