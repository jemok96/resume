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

import java.util.Date;
import java.util.List;


@Slf4j
@Controller
public class NoticeController {
    private final UserImageService imageService;
    private final NoticeService noticeService;

    public NoticeController(UserImageService imageService, NoticeService noticeService) {
        this.imageService = imageService;
        this.noticeService = noticeService;
    }

    @GetMapping("/notices")
    public String NoticeMain(@SessionAttribute(value = "userSession",required = false)String userId, Model model,SearchCondition sc){
        model.addAttribute("userImage",imageService.findImageById(userId));

         int totalCnt = noticeService.searchResultCnt(sc);
        model.addAttribute("totalCnt", totalCnt);

        PageHandler pageHandler = new PageHandler(totalCnt, sc);

        List<NoticeDTO> notice = noticeService.searchSelectPage(sc);


        model.addAttribute("notice", notice);
        model.addAttribute("ph", pageHandler);
        return "notice/mainForm";
    }


    @GetMapping("/notices/new")
    public String NoticeWritePage(@SessionAttribute("userSession")String userId,Model model){
        model.addAttribute("userImage",imageService.findImageById(userId));
        model.addAttribute("notice", new NoticeDTO());
        return "notice/addForm";
    }
    @PostMapping("/notices/new")
    public String NoticeWrite(@SessionAttribute("userSession")String userId, Model model,
                              @Validated @ModelAttribute("notice")NoticeDTO notice, BindingResult bindingResult){
        model.addAttribute("userImage",imageService.findImageById(userId));
        if(bindingResult.hasErrors()){
            return "notice/addForm";
        }
        notice.setUserid(userId);
        noticeService.saveNotice(notice);
        return "redirect:/notices";
    }
    @GetMapping("/notices/detail/{num}")
    public String NoticeDetail(@PathVariable Integer num,@SessionAttribute("userSession")String userId, Model model){
        model.addAttribute("notice",noticeService.findByNum(num));
        model.addAttribute("userImage",imageService.findImageById(userId));
        return "notice/detailForm";
    }
    @GetMapping("/notices/{num}")
    public String NoticeModifyMain(@PathVariable Integer num,@SessionAttribute("userSession")String userId, Model model){
        model.addAttribute("notice",noticeService.findByNum(num));
        model.addAttribute("userImage",imageService.findImageById(userId));
        return "notice/modifyForm";
    }
    @PatchMapping("/notices/{num}")
    @ResponseBody
    public Integer NoticeModify(@PathVariable Integer num, @SessionAttribute("userSession")String userId, Model model
    , @Validated @RequestBody NoticeDTO notice , BindingResult bindingResult){
        model.addAttribute("userImage",imageService.findImageById(userId));

        log.info("notice={}",notice);

        if(bindingResult.hasErrors()){
            model.addAttribute("notice",notice);
            return 400;
        }
        return noticeService.updateNotice(NoticeDTO.builder().
                num(num)
                .title(notice.getTitle())
                .contents(notice.getContents())
                .up_date(new Date()).build());


    }
    @DeleteMapping("/notices/{num}")
    @ResponseBody
    public Integer DeleteNotice(@PathVariable Integer num, @SessionAttribute("userSession")String userId,RedirectAttributes attr){
        return noticeService.deleteNotice(num);
    }
}
