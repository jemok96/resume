package com.resume.controller;

import com.resume.dto.ExperienceDto;
import com.resume.dto.RegisterDto;
import com.resume.dto.UserInfoDto;
import com.resume.service.MainService;
import com.resume.service.UserImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        RegisterDto userinfo = null;
        UserInfoDto usersubinfo = null;
        List<ExperienceDto> experienceinfo = null;

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

        UserInfoDto usersubinfo = null;
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


        UserInfoDto usersubinfo = null;
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
    public String experienceAdd(HttpSession session , ExperienceDto experidto) {

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

            ExperienceDto experienceinfo = null;

            experienceinfo = mainService.experienceInfo2(seqno1);

            mv.addObject("experienceinfo" ,experienceinfo);
            mv.addObject("userImage",imageService.findImageById(sessionId));
            mv.setViewName("main/experienceModify");
            return mv;
        }



    }

    @PostMapping("/main/experienceModify")
    public String experienceModify( ExperienceDto experidto) {

        mainService.experienceModify(experidto);


        return "redirect:/main#experience" ;
    }

    @GetMapping("/main/skillUpdate")
    public ModelAndView skillUpdate(@SessionAttribute("userSession")String sessionid , ModelAndView mv){


        mv.addObject("userImage",imageService.findImageById(sessionid));
        List<String> skillnmarr = mainService.selectSkillname(sessionid);

        mv.addObject("skillnmarr" ,skillnmarr );

        mv.setViewName("main/skillUpdate");
        return mv;
    }
    @PostMapping("/main/skillSave")
    public String skillSave(HttpServletRequest request , @SessionAttribute("userSession")String sessionid) {
        String[] selectedImages = request.getParameterValues("image[]");
        Map map = new HashMap();
        map.put("userid" , sessionid);

        mainService.skillDelete(map);

        for(int i =0 ; i < selectedImages.length ; i++){
            String skillname = selectedImages[i];
            String skillurl =
                    "https://s3.ap-northeast-2.amazonaws.com/resume.filesfolder/skillimage/" + selectedImages[i];
            map.put("skillurl" , skillurl);
            map.put("skillname" , skillname);
            mainService.skillSave(map);

        }


        return "redirect:/main";
    }

    @GetMapping("/main/skillAjax")
    @ResponseBody
    public List<String>  handleSkillAjaxRequest(@RequestParam("sessionid") String sessionId) {



        // 이 예시에서는 간단한 배열을 반환합니다.
        List<String> response = mainService.selectSkill(sessionId);

        for (int i = 0 ; i < response.size() ; i++){
            System.out.println(response.get(i));
        }

        return response;
    }




}
