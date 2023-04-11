package com.resume.controller;



import com.resume.dto.EmailCheckDTO;
import com.resume.dto.LoginUserDTO;
import com.resume.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Random;


@Slf4j
@Controller
public class LoginController {

    private final JavaMailSender javaMailSender;
    private final LoginService loginService;

    public LoginController(JavaMailSender javaMailSender, LoginService loginService) {
        this.javaMailSender = javaMailSender;
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new LoginUserDTO());
        return "login/login";
    }

    @PostMapping("/login")
    public String loginCheck(@Validated @ModelAttribute("user") LoginUserDTO user, BindingResult bindingResult,
                             Model model, HttpServletRequest request) {


        if (bindingResult.hasErrors()) {
            return "login/login";
        }

        Boolean status = loginService.checkUser(user);
        model.addAttribute("user", user);
        if (status) {
            HttpSession session = request.getSession();
            session.setAttribute("userSession",user.getUserid());
            return "main/main";
        } else {
            model.addAttribute("status", true);
            return "login/login";
        }

    }

    @GetMapping("/findById")
    public String findId(Model model) {
        model.addAttribute("email",new EmailCheckDTO());
        return "login/find/findById";
    }

    @PostMapping("/checkid")
    public String checkId(@Validated @ModelAttribute("email") EmailCheckDTO email, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            return "login/find/findById";
        }
        Boolean status = loginService.CheckEmail(email);
        model.addAttribute("email",email);
        String ranNum = "0";
        ranNum = createRandomNum();
        model.addAttribute("ranNum",ranNum);
        if (status) {
            mailSend(email.getEmail(),ranNum);
            return "login/find/findById";
        }
        return "login/find/findById";
    }

    @GetMapping("/findSuccess")
    public String findByIdSuccess(){

        return "login/find/findsuccess";
    }










    private void mailSend(String email,String randomNum) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("이메일 인증");
        simpleMailMessage.setText(randomNum);
        javaMailSender.send(simpleMailMessage);
    }


    public String createRandomNum() {
        Random random = new Random();
        int createNum = 0;
        String ranNum = "";
        int letter = 6;
        String resultNum = "";
        for (int i = 0; i < letter; i++) {
            createNum = random.nextInt(9);
            ranNum = Integer.toString(createNum);
            resultNum += ranNum;
        }
        return resultNum;
    }
}
