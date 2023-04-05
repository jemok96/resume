package com.resume.controller;


import com.resume.config.MailService;
import com.resume.dto.LoginUserDTO;
import com.resume.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Controller
public class LoginController {


    private final LoginService loginService;


    public LoginController(LoginService loginService ) {
        this.loginService = loginService;

    }

    @GetMapping("/login")
    public String login() {

        return "login";
    }

    @PostMapping("/login")
    public String loginCheck(@RequestParam("userid") String userid, @RequestParam("password") String password) {

        log.info("userid = {}", userid);
        log.info("password = {}", password);
        LoginUserDTO loginUserDTO = loginService.checkUser(userid);
        System.out.println("loginUserDTO = " + loginUserDTO);

        return "login";

    }

    @GetMapping("/checkid")
    public String checkId(@RequestParam("email") String email) {
        log.info("email = {}", email);
        MailService mail = new MailService();
        mail.sendMail();
        return "/login";
    }



}

