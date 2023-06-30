package com.resume.controller;

import com.resume.dto.NoticeDTO;
import com.resume.dto.PageHandler;
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
    public String NoticeMain(@SessionAttribute("userSession")String userId, Model model
    , @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize){
        model.addAttribute("userImage",imageService.findImageById(userId));
        int totalCount = noticeService.noticeCount();
        PageHandler pageHandler = new PageHandler(totalCount,page,pageSize);
        Map<String, Integer> map = new HashMap();
        map.put("offset", (page - 1) * pageSize);
        map.put("pageSize", pageSize);

        List<NoticeDTO> notice  = noticeService.findAll(map);

        model.addAttribute("ph", pageHandler);
        model.addAttribute("page", page);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("count",totalCount);
        model.addAttribute("notice",notice);
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
