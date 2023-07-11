package com.resume.controller;

import com.resume.Repository.BoardDAO;
import com.resume.dto.BoardDTO;
import com.resume.dto.NoticeDTO;
import com.resume.dto.PageHandler;
import com.resume.dto.SearchCondition;
import com.resume.service.BoardService;
import com.resume.service.UserImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
@Slf4j
public class BoardController {
    private final UserImageService imageService;
    private final BoardService boardService;

    public BoardController(UserImageService imageService, BoardService boardService) {
        this.imageService = imageService;
        this.boardService = boardService;
    }

    @RequestMapping("/board")
    public String mainForm(@SessionAttribute("userSession")String userId, Model model, SearchCondition sc){
        model.addAttribute("userImage",imageService.findImageById(userId));

        int totalCnt = boardService.searchResultCnt(sc);
        model.addAttribute("totalCnt", totalCnt);

        PageHandler pageHandler = new PageHandler(totalCnt, sc);

        List<BoardDTO> board = boardService.searchSelectPage(sc);


        model.addAttribute("board", board);
        model.addAttribute("ph", pageHandler);

        return "board/mainForm";
    }
}
