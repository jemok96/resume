package com.resume.controller;



import com.resume.dto.LoginUserDTO;
import com.resume.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


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
        model.addAttribute("user",new LoginUserDTO());
        return "login/login";
    }

    @PostMapping("/login")
    public String loginCheck(@Validated @ModelAttribute("user") LoginUserDTO user, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,Model model) {
        log.info("user={}",user);
        //오류 있을경우 error메세지 가지고 login페이지로 돌아가서 표시
        if (bindingResult.hasErrors()){
            return "login/login";
        }

        String message = loginService.checkUser(user);
        log.info("message={}",message);
        if(message.equals("success")){
            return "login";
        }
        else{
            redirectAttributes.addAttribute("status",true);
            return "redirect:/login";
        }


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
        simpleMailMessage.setSubject("이메일 인증");
        simpleMailMessage.setText("Text Mail Sender");

        javaMailSender.send(simpleMailMessage);
    }




}

