package com.resume.controller;

import org.springframework.stereotype.Controller;

@Controller
public class BoardController {
    public String mainForm(){
        return "login/login";
    }
}
