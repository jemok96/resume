package com.resume.controller;



import com.resume.dto.LoginUserDTO;
import com.resume.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Slf4j
@Controller
public class LoginController {

    private final JavaMailSender javaMailSender;
    private final LoginService loginService;

    public LoginController(JavaMailSender javaMailSender, LoginService loginService) {
        this.javaMailSender = javaMailSender;
        this.loginService = loginService;
        log.info("login={}",loginService);


    }

    @GetMapping("/login")
    public String login() {
        return "login/login";
    }

    @PostMapping("/login")
    public String loginCheck(@RequestParam("userid") String userid, @RequestParam("password") String password) {

        log.info("userid = {}", userid);
        log.info("password = {}", password);
        LoginUserDTO loginUserDTO = loginService.checkUser(userid);
        System.out.println("loginUserDTO = " + loginUserDTO);
        return "login/login";

    }

    @GetMapping("/findid")
    public String findId(){
        return "login/findid";
    }
    @GetMapping("/checkid")
    public String checkId(@RequestParam("email")String email){
        mailSend(email);
        System.out.println("email = " + email);
        return "login/findid";
    }




    private void mailSend(String email) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("title");
        simpleMailMessage.setText("Text Mail Sender");

        javaMailSender.send(simpleMailMessage);
    }




}

