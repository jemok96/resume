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

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class RegisterController {


    private final RegisterService registerService;


    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @GetMapping("/register")
    public String signup(){

        return "register/register";
    }

    @PostMapping("/register/save")
    public String signupsave( @ModelAttribute("user") RegisterDTO user, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes, ModelAndView mv) {
        int result = registerService.saveuser(user);

        log.info(result + "결과");

        return "main/main";

    }

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
