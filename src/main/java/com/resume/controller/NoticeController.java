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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
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
    @GetMapping("/notice/{num}")
    public String NoticeDetail(@PathVariable Integer num,@SessionAttribute("userSession")String userId, Model model){
        model.addAttribute("notice",noticeService.findByNum(num));
        model.addAttribute("userImage",imageService.findImageById(userId));
        return "notice/detailForm";
    }
    @GetMapping("/notice/{num}/modify")
    public String NoticeModifyMain(@PathVariable Integer num,@SessionAttribute("userSession")String userId, Model model){
        model.addAttribute("notice",noticeService.findByNum(num));
        model.addAttribute("userImage",imageService.findImageById(userId));
        return "notice/modifyForm";
    }
    @PostMapping("/notice/{num}/modify")
    public String NoticeModify(@PathVariable Integer num, @SessionAttribute("userSession")String userId, Model model
    , @Validated NoticeDTO notice , BindingResult bindingResult, RedirectAttributes attr){
        model.addAttribute("userImage",imageService.findImageById(userId));

        if(bindingResult.hasErrors()){
            model.addAttribute("notice",noticeService.findByNum(num));
            return "notice/modifyForm";
        }
        log.info("notice={}",notice);
        noticeService.updateNotice(NoticeDTO.builder().
                num(num)
                .title(notice.getTitle())
                .contents(notice.getContents())
                .up_date(new Date()).build());
        attr.addFlashAttribute("statusModify","OK");
        return "redirect:/notice";
    }
    @PostMapping("/notice/{num}/delete")
    public String DeleteNotice(@PathVariable Integer num, @SessionAttribute("userSession")String userId,RedirectAttributes attr){
        noticeService.deleteNotice(num);
        attr.addFlashAttribute("statusDel","OK");
        return "redirect:/notice";
    }
}
