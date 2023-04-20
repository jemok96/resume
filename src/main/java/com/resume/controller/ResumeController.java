package com.resume.controller;

import com.resume.dto.ResumeDTO;
import com.resume.service.ResumeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        if(session !=null){
            model.addAttribute("resume",service.findResumeById(session));
        }
        return "resume/mainForm";
    }
    @GetMapping("/resume/add")
    public String mainAddForm(Model model){
        model.addAttribute("resume",new ResumeDTO());
        return "resume/addForm";
    }
    @PostMapping("/resume/add")
    public String addCheck(@Validated @ModelAttribute("resume")ResumeDTO dto,
                           BindingResult bindingResult,@SessionAttribute(value = "userSession" ,required = false)String userid,
                           Model model){
        if(bindingResult.hasErrors()){
            return "resume/addForm";
        }
        ResumeDTO userInput = ResumeDTO.builder()
                        .userid(userid)
                        .title(dto.getTitle())
                        .contents(dto.getContents()).build();
        service.insertResume(userInput);
        model.addAttribute("resume",dto);
        return "redirect:/resume";
    }
    @GetMapping("/resume/modify/{rno}")
    public String modifyResume(@PathVariable Integer rno,Model model, @SessionAttribute(value = "userSession",required = true)String user){
        model.addAttribute("resume",service.selectByNo(rno));
        return "resume/modifyForm";
    }
    @PostMapping("/resume/modify/{rno}")
    public String modifyCheck(@Validated @ModelAttribute("resume")ResumeDTO dto, BindingResult bindingResult,
                              @PathVariable Integer rno, Model model, @SessionAttribute(value = "userSession",required = true)String user,
                              RedirectAttributes attr){
        if(bindingResult.hasErrors()){
            return "resume/modifyForm";
        }
        ResumeDTO resume = ResumeDTO.builder()
                        .title(dto.getTitle())
                        .contents(dto.getContents())
                        .resumeno(rno)
                        .userid(user).build();
        service.updateResume(resume);
        attr.addFlashAttribute("status","ok");
        return "redirect:/resume/modify/{rno}";
    }

    @PostMapping("/resume/delete/{rno}")
    public String deleteResume(@PathVariable Integer rno,RedirectAttributes attr){
        service.deleteOne(rno);
        attr.addFlashAttribute("status","ok");
        return "redirect:/resume";
    }
}
