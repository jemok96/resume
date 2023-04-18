package com.resume.controller;

import com.resume.dto.ResumeDTO;
import com.resume.service.ResumeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;


@Controller
@Slf4j
public class ResumeController {

    private final ResumeService service;
    public ResumeController(ResumeService service) {
        this.service = service;
    }

    @GetMapping("/resume")
    public String main(@SessionAttribute(value = "userSession" ,required = false)String session, Model model){
        model.addAttribute("userId",session);

        if(session !=null){
            List<ResumeDTO> dto = service.findResumeById(session);
            model.addAttribute("resume",dto);
        }
        return "resume/mainresume";
    }
}
