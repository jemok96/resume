package com.resume.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class BoardController {
    @RequestMapping("/jenkins/buildTest")
    public String mainForm(){
        log.info("test Code");
        return "login/login";
    }
}
