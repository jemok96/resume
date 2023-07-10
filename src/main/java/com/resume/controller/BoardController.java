package com.resume.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {
    @RequestMapping("/jenkins/buildTest")
    public String mainForm(){
        return "login/login";
    }
}
