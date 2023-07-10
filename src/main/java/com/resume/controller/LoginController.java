package com.resume.controller;



import com.resume.config.AsyncConfig;
import com.resume.dto.EmailCheckDTO;
import com.resume.dto.LoginUserDTO;
import com.resume.dto.UserDTO;
import com.resume.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Random;


@Slf4j
@Controller
public class LoginController {

    private final JavaMailSender javaMailSender;
    private final LoginService loginService;
    private final AsyncConfig config;


    public LoginController(JavaMailSender javaMailSender, LoginService loginService, AsyncConfig config) {
        this.javaMailSender = javaMailSender;
        this.loginService = loginService;
        this.config = config;
    }

    @GetMapping("/login")
    public String login(Model model,@SessionAttribute(value = "userSession",required = false)String session) {
        model.addAttribute("user", new LoginUserDTO());
        if(session !=null){
            return "redirect:/main";
        }
        return "login/login";
    }

    @PostMapping("/login")
    public String loginCheck(@Validated @ModelAttribute("user") LoginUserDTO user, BindingResult bindingResult,
                             Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "login/login";
        }
        model.addAttribute("user", user);
        if (loginService.checkUser(user)) {

            session.setAttribute("userSession",user.getUserid());
            return "redirect:/main";
        } else {
            model.addAttribute("status", true);
            return "login/login";
        }

    }
    @GetMapping("/logout")
    public String logOut(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session!=null){
            session.invalidate();
        }
        return "redirect:/resume";
    }

    @GetMapping("/login/search")
    public String findId(Model model) {
        model.addAttribute("email",new EmailCheckDTO());
        return "login/find/findById";
    }

    @PostMapping("/login/search")
    public String checkId(@Validated @ModelAttribute("email") EmailCheckDTO email, BindingResult bindingResult, Model model,
                          HttpSession session) {
        if (bindingResult.hasErrors()) {
            log.info("error={}", bindingResult);
            return "login/find/findById";
        }

        Boolean status = loginService.CheckEmail(email);
        model.addAttribute("email", email);

        if (status) {
            String ranNum = createRandomNum();
            session.setAttribute("email",email.getEmail());
            session.setAttribute("ranNum",ranNum);
            config.mailSend(email.getEmail(), ranNum);
        }

        return "login/find/findById";
    }
    @PostMapping("/verify")
    @ResponseBody
    public String verifyRandomNum(@RequestParam String userInput,HttpSession session ){
        String ranNum = (String) session.getAttribute("ranNum");
        if(userInput.equals(ranNum)){
            return "success";
        }
        return "fail";


    }
    @GetMapping("/login/search/detail")
    public String findByIdSuccess(@SessionAttribute(value = "email",required = false) String email,Model model){
        log.info("email={}",email);
        log.info("{userId={}}",loginService.findIdByEmail(email));
        model.addAttribute("user",loginService.findIdByEmail(email));
        return "login/find/findUserId";
    }

    public String createRandomNum() {
        Random random = new Random();
        StringBuilder resultNum = new StringBuilder();
        int letter = 6;
        for (int i = 0; i < letter; i++) {
            int createNum = random.nextInt(10);
            resultNum.append(createNum);
        }

        return resultNum.toString();
    }

}


