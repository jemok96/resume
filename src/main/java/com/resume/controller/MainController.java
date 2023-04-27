package com.resume.controller;

import com.resume.dto.ExperienceDTO;
import com.resume.dto.RegisterDTO;
import com.resume.dto.UserInfoDTO;
import com.resume.service.MainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


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
        ExperienceDTO experienceinfo = null;

        //유저 개인정보
        userinfo =  mainService.userInfo(sessionid);
        usersubinfo = mainService.usersubinfo(sessionid);

        //유저 경력기술서
        experienceinfo = mainService.experienceinfo(sessionid);

        if(usersubinfo==null) {
            mainService.infoinsert(sessionid);
            usersubinfo = mainService.usersubinfo(sessionid);
        }

        mv.addObject("userinfo" ,userinfo );
        mv.addObject("usersubinfo" ,usersubinfo);
        mv.addObject("experienceinfo" ,experienceinfo);
        mv.setViewName("main/main");

        return mv;


    }
    @GetMapping("/main/infoupdate")
    public ModelAndView infoupdate(HttpSession session , ModelAndView mv ){

        String sessionid = (String) session.getAttribute("userSession");

        UserInfoDTO usersubinfo = null;
        usersubinfo = mainService.usersubinfo(sessionid);

        mv.addObject("usersubinfo" ,usersubinfo);

        mv.setViewName("main/UserInfoModify");

        return mv;
    }

    @PostMapping("/main/infoupdate/save")
    public String infoSave(HttpServletRequest request , HttpSession session) {

        Map map = new HashMap();
        String sessionid = (String) session.getAttribute("userSession");


        UserInfoDTO usersubinfo = null;
        usersubinfo = mainService.usersubinfo(sessionid);

        String blog = request.getParameter("blogurl");
        String git = request.getParameter("giturl");
        String intro = request.getParameter("introduction");


        map.put("blog" , blog);
        map.put("git" , git);
        map.put("intro" , intro);
        map.put("userid" ,sessionid );


        mainService.infoupdate(map);




        return "redirect:/main" ;
    }
}
