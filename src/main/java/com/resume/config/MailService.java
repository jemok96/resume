package com.resume.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Slf4j
@Service
public class MailService{
    MailConfig mailConfig = new MailConfig();
    private JavaMailSender javaMailSender =mailConfig.javaMailService();
//    @Autowired
//    private JavaMailSender javaMailSender;

    public void sendMail() {


        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo("jemok9605@naver.com");
        simpleMailMessage.setSubject("이메일 인증 발송");
        simpleMailMessage.setFrom("rudnf9605@gmail.com");
        log.info("email!");
        log.info("simple={}",simpleMailMessage);
        log.info("javaMail={}",javaMailSender);
        simpleMailMessage.setText("hi");
//        simpleMailMessage.setText(randomNum.toString());
        javaMailSender.send(simpleMailMessage);

    }
}
