package com.resume.service;

import com.resume.Repository.BoardDao;
import com.resume.dto.BoardDto;
import com.resume.dto.CommentDto;
import com.resume.dto.SearchCondition;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BoardService {
    private final BoardDao dao;

    public BoardService(BoardDao dao) {
        this.dao = dao;
    }

    public int saveBoard(BoardDto dto) {
        return dao.saveBoard(dto);
    }

    public int deleteBoard(int num){
        return dao.deleteBoard(num);
    }
    public BoardDto findByNum(int num){
        return dao.findByNum(num);
    }

    public List<BoardDto> findAll(Map map){
        return dao.findAll(map);
    }

    public int searchResultCnt(SearchCondition sc){
        return dao.searchResultCnt(sc);
    }
    public List<BoardDto>searchSelectPage(SearchCondition sc){
        return dao.searchSelectPage(sc);
    }

    public int updateBoard(BoardDto dto){
        try {
            int result = dao.updateBoard(dto);
            if (result == 1) {
                return 200;
            } else {
                return 500;
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to update resume", e);
        }
    }
    public int insertComment(CommentDto dto){
        return dao.insertComment(dto);
    }
    public List<CommentDto> getComments(int boardno){
        return dao.getComments(boardno);
    }
    public int deleteComment(int commentno){
        return dao.deleteComment(commentno);
    }

}
