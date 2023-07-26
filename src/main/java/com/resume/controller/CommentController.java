package com.resume.controller;

import com.resume.dto.CommentDto;
import com.resume.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class CommentController {
    private final BoardService boardService;

    public CommentController(BoardService boardService) {
        this.boardService = boardService;
    }


    @GetMapping("/comments/{num}")
    @ResponseBody
    public List<CommentDto> getComments(@PathVariable Integer num, @SessionAttribute("userSession")String userId){
        List<CommentDto> comments = boardService.getComments(num);
        log.info("num = {}",num);
        log.info("comments={}",comments);
        return comments;
    }


    @PostMapping("/comments/{num}")
    @ResponseBody
    public Integer InsertComments(@PathVariable Integer num, @SessionAttribute("userSession")String userId
            ,@RequestParam("cmcontent") String contents){
        CommentDto dto = CommentDto.builder()
                .boardno(num)
                .writer(userId)
                .contents(contents)
                .build();
        return boardService.insertComment(dto);
    }
    @DeleteMapping("/comments/{commentNo}")
    @ResponseBody
    public Integer InsertComments(@PathVariable Integer commentNo, @SessionAttribute("userSession")String userId){

        log.info("commentNo ={}",commentNo);
        return boardService.deleteComment(commentNo);
    }
}
