package com.resume.controller;



import com.resume.dto.EmailCheckDTO;
import com.resume.dto.LoginUserDTO;
import com.resume.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.server.Session;
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
import java.nio.file.Files;
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

    @GetMapping("/findById")
    public String findId(Model model) {
        model.addAttribute("email",new EmailCheckDTO());
        return "login/find/findById";
    }

    @PostMapping("/checkid")
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
            session.setAttribute("ranNum",ranNum);
            mailSend(email.getEmail(), ranNum);
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
    @GetMapping("/findSuccess/{email}")
    public String findByIdSuccess(@PathVariable String email){
        log.info("email={}",email);

        return "login/find/findUserId";
    }

    @Async
    public void mailSend(String email, String randomNum) { //private에서는 비동기 작동안함
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("이메일 인증");
        simpleMailMessage.setText(randomNum);
        javaMailSender.send(simpleMailMessage);
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


