package com.resume.controller;

import com.resume.dto.RegisterDTO;
import com.resume.service.RegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class RegisterController {


    private final RegisterService registerService;


    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    //회원가입 페이지 이동
    @GetMapping("/register")
    public String signup(){

        return "register/register";
    }

    //회원가입 저장
    @PostMapping("/register/save")
    public String signupsave( @ModelAttribute("user") RegisterDTO user, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes, ModelAndView mv) {
        int result = registerService.saveuser(user);

        log.info(result + "결과");

        return "login/login";

    }

    //회원가입 시 아이디 중복 확인
    @PostMapping("register/check")
    @ResponseBody
    public Map<Object, Object> idcheck(@RequestBody String userid) {

        int count = 0;
        Map<Object, Object> map = new HashMap<Object, Object>();

        count = registerService.idcheck(userid);
        map.put("cnt", count);

        return map;
    }






}
