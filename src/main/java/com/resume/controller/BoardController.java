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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    @GetMapping("/board/new")
    public String NoticeWritePage(@SessionAttribute("userSession")String userId,Model model){
        model.addAttribute("userImage",imageService.findImageById(userId));
        model.addAttribute("board", new BoardDTO());
        return "board/addForm";
    }
    @PostMapping("/board/new")
    public String NoticeWrite(@SessionAttribute("userSession")String userId, Model model,
                              @Validated @ModelAttribute("board")BoardDTO board, BindingResult bindingResult){
        model.addAttribute("userImage",imageService.findImageById(userId));
        if(bindingResult.hasErrors()){
            return "board/addForm";
        }
        board.setUserid(userId);
        boardService.saveBoard(board);
        return "redirect:/board";
    }
    @GetMapping("/board/detail/{num}")
    public String NoticeDetail(@PathVariable Integer num,@SessionAttribute("userSession")String userId, Model model){
        model.addAttribute("board",boardService.findByNum(num));
        model.addAttribute("userImage",imageService.findImageById(userId));
        return "board/detailForm";
    }

    @GetMapping("/board/{num}")
    public String NoticeModifyMain(@PathVariable Integer num,@SessionAttribute("userSession")String userId, Model model){
        model.addAttribute("board",boardService.findByNum(num));
        model.addAttribute("userImage",imageService.findImageById(userId));
        return "board/modifyForm";
    }
    @PatchMapping("/board/{num}")
    @ResponseBody
    public Integer NoticeModify(@PathVariable Integer num, @SessionAttribute("userSession")String userId, Model model
            , @Validated @RequestBody BoardDTO board , BindingResult bindingResult){
        model.addAttribute("userImage",imageService.findImageById(userId));

        log.info("board={}",board);

        if(bindingResult.hasErrors()){
            model.addAttribute("board",board);
            return 400;
        }
        return boardService.updateBoard(BoardDTO.builder().
                boardno(num)
                .title(board.getTitle())
                .contents(board.getContents())
                .up_date(new Date()).build());


    }
    @DeleteMapping("/board/{num}")
    @ResponseBody
    public Integer DeleteNotice(@PathVariable Integer num, @SessionAttribute("userSession")String userId){
        return boardService.deleteBoard(num);
    }
}
