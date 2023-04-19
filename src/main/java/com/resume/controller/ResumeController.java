package com.resume.controller;

import com.resume.dto.ResumeDTO;
import com.resume.service.ResumeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
        return "resume/mainForm";
    }
    @GetMapping("/resume/add")
    public String mainAddForm(){
        return "resume/addForm";
    }
    @PostMapping("/resume/add")
    public String addCheck(@Validated @ModelAttribute("resume")ResumeDTO dto,
                           BindingResult bindingResult,@SessionAttribute(value = "userSession" ,required = false)String userid){
        if(bindingResult.hasErrors()){
            log.info("errors={}",bindingResult);
        }
        ResumeDTO userInput = ResumeDTO.builder()
                        .userid(userid)
                                .title(dto.getTitle())
                                        .contents(dto.getContents()).build();
        service.insertResume(userInput);
        return "redirect:/resume";
    }
}
