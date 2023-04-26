package com.resume.controller;

import com.resume.dto.UserDTO;
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

import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
public class MemberManageController {
    private final ManageMentService service;

    public MemberManageController(ManageMentService service) {
        this.service = service;
    }

    @GetMapping("/profile")
    public String main(@SessionAttribute("userSession")String userId, Model model){
        log.info("userId={}",userId);
        model.addAttribute("userId",userId);
        return "management/mainForm";
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
    @PostMapping("/profile/{userId}")
    public String user(@PathVariable("userId") String userId,Model model,@SessionAttribute("userSession")
            String sessionId){
        log.info("userId={}",userId);
        model.addAttribute("user",service.findById(userId,sessionId));
        return "management/modifyForm";
    }


}
