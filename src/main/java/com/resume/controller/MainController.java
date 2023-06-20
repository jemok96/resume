package com.resume.controller;

import com.resume.dto.ExperienceDTO;
import com.resume.dto.RegisterDTO;
import com.resume.dto.UserInfoDTO;
import com.resume.service.MainService;
import com.resume.service.UserImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Controller
public class MainController {

    private final MainService mainService;
    private final UserImageService imageService;
    public MainController(MainService mainService, UserImageService imageService) {
        this.mainService = mainService;
        this.imageService = imageService;
    }


    //로그인 후 메인 페이지 이동
    @GetMapping("/main")

    public ModelAndView mainPage(HttpSession session , ModelAndView mv ){

        String sessionid = (String) session.getAttribute("userSession");
        RegisterDTO userinfo = null;
        UserInfoDTO usersubinfo = null;
        List<ExperienceDTO> experienceinfo = null;

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
        mv.addObject("userImage",imageService.findImageById(sessionid));
        mv.setViewName("main/main");

        return mv;


    }
    @GetMapping("/main/infoupdate")
    public ModelAndView infoupdate(HttpSession session , ModelAndView mv ){

        String sessionid = (String) session.getAttribute("userSession");

        UserInfoDTO usersubinfo = null;
        usersubinfo = mainService.usersubinfo(sessionid);

        mv.addObject("usersubinfo" ,usersubinfo);
        mv.addObject("userImage",imageService.findImageById(sessionid));
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

    @GetMapping("/main/experienceAdd")
    public String experienceAdd(@SessionAttribute("userSession")String sessionid, Model model){
        model.addAttribute("userImage",imageService.findImageById(sessionid));
        return "main/experienceAdd";
    }

    @PostMapping("/main/experienceAdd/save")
    public String experienceAdd(HttpSession session , ExperienceDTO experidto) {

        Map map = new HashMap();
        String sessionid = (String) session.getAttribute("userSession");

        experidto.setUserid(sessionid);

        mainService.experienceAdd(experidto);


        return "redirect:/main#experience" ;
    }

    @PostMapping("/main/experienceChange")
    public ModelAndView experienceDelete(HttpServletRequest request , ModelAndView mv,@SessionAttribute("userSession")String sessionId) {


        String action = request.getParameter("action");
        String seqno1 = request.getParameter("seqno");

        if(action.equals("delete")){
            mainService.experienceDelete(seqno1);
            mv.setViewName("redirect:/main#experience");
            return mv;
        }else{

            ExperienceDTO experienceinfo = null;

            experienceinfo = mainService.experienceInfo2(seqno1);

            mv.addObject("experienceinfo" ,experienceinfo);
            mv.addObject("userImage",imageService.findImageById(sessionId));
            mv.setViewName("main/experienceModify");
            return mv;
        }



    }

    @PostMapping("/main/experienceModify")
    public String experienceModify( ExperienceDTO experidto) {

        log.info("성진 " + experidto);

        mainService.experienceModify(experidto);


        return "redirect:/main#experience" ;
    }

    @GetMapping("/main/skillUpdate")
    public ModelAndView skillUpdate(@SessionAttribute("userSession")String sessionid , ModelAndView mv){
        mv.addObject("userImage",imageService.findImageById(sessionid));
        mv.setViewName("main/skillUpdate");
        return mv;
    }


}
