package com.resume.controller;

import com.resume.dto.RegisterDTO;
import com.resume.dto.UserInfoDTO;
import com.resume.service.MainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


@Slf4j
@Controller
public class MainController {

    private final MainService mainService;

    public MainController(MainService mainService) {
        this.mainService = mainService;
    }


    //로그인 후 메인 페이지 이동
    @GetMapping("/main")

    public ModelAndView mainPage(HttpSession session , ModelAndView mv ){

        String sessionid = (String) session.getAttribute("userSession");
        RegisterDTO userinfo = null;
        UserInfoDTO usersubinfo = null;

        userinfo =  mainService.userInfo(sessionid);

        mv.addObject("userinfo" ,userinfo );
        mv.setViewName("main/main");

        return mv;


    }
}
