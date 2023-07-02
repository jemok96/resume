package com.resume.controller;

import com.resume.dto.NoticeDTO;
import com.resume.dto.PageHandler;
import com.resume.dto.SearchCondition;
import com.resume.service.NoticeService;
import com.resume.service.UserImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class NoticeController {
    private final UserImageService imageService;
    private final NoticeService noticeService;

    public NoticeController(UserImageService imageService, NoticeService noticeService) {
        this.imageService = imageService;
        this.noticeService = noticeService;
    }

    @GetMapping("/notice")
    public String NoticeMain(@SessionAttribute(value = "userSession",required = false)String userId, Model model
    ,  SearchCondition sc){
        model.addAttribute("userImage",imageService.findImageById(userId));

         int totalCnt = noticeService.searchResultCnt(sc);
        model.addAttribute("totalCnt", totalCnt);

        PageHandler pageHandler = new PageHandler(totalCnt, sc);

        List<NoticeDTO> notice = noticeService.searchSelectPage(sc);
        log.info("pageHandler={}",pageHandler);
        log.info("notice={}",notice);


        model.addAttribute("notice", notice);
        model.addAttribute("ph", pageHandler);
        return "notice/mainForm";
    }


    @GetMapping("/notice/write")
    public String NoticeWritePage(@SessionAttribute("userSession")String userId,Model model){
        model.addAttribute("userImage",imageService.findImageById(userId));
        model.addAttribute("notice", new NoticeDTO());
        return "notice/addForm";
    }
    @PostMapping("/notice/write")
    public String NoticeWrite(@SessionAttribute("userSession")String userId, Model model,
                              @Validated @ModelAttribute("notice")NoticeDTO notice, BindingResult bindingResult){
        model.addAttribute("userImage",imageService.findImageById(userId));
        if(bindingResult.hasErrors()){
            return "notice/addForm";
        }
        notice.setUserid(userId);
        noticeService.saveNotice(notice);
        return "redirect:/notice";
    }
}
