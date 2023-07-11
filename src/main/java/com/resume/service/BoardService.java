package com.resume.service;

import com.resume.Repository.BoardDAO;
import com.resume.Repository.NoticeDAO;
import com.resume.dto.BoardDTO;
import com.resume.dto.NoticeDTO;
import com.resume.dto.SearchCondition;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BoardService {
    private final BoardDAO dao;

    public BoardService(BoardDAO dao) {
        this.dao = dao;
    }

    public int saveNotice(BoardDTO dto) {
        return dao.saveNotice(dto);
    }

    public int deleteNotice(int num){
        return dao.deleteNotice(num);
    }
    public BoardDTO findByNum(int num){
        return dao.findByNum(num);
    }

    public List<BoardDTO> findAll(Map map){
        return dao.findAll(map);
    }

    public int searchResultCnt(SearchCondition sc){
        return dao.searchResultCnt(sc);
    }
    public List<BoardDTO>searchSelectPage(SearchCondition sc){
        return dao.searchSelectPage(sc);
    }

    public int updateNotice(BoardDTO dto){
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

}
