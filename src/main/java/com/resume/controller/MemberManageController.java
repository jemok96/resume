package com.resume.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@Slf4j
public class MemberManageController {
    @GetMapping("/profile")
    public String main(@SessionAttribute("userSession")String userId, Model model){
        log.info("userId={}",userId);
        model.addAttribute("userId",userId);

        return "management/mainForm";

    }
}
