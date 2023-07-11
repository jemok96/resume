package com.resume.service;

import com.resume.Repository.NoticeDAO;
import com.resume.dto.NoticeDTO;
import com.resume.dto.SearchCondition;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NoticeService {
    private final NoticeDAO dao;

    public NoticeService(NoticeDAO dao) {
        this.dao = dao;
    }

    public int saveNotice(NoticeDTO dto) {
        return dao.saveNotice(dto);
    }

    public int deleteNotice(int num){
        return dao.deleteNotice(num);
    }
    public NoticeDTO findByNum(int num){
        return dao.findByNum(num);
    }

    public List<NoticeDTO> findAll(Map map){
        return dao.findAll(map);
    }

    public int searchResultCnt(SearchCondition sc){
        return dao.searchResultCnt(sc);
    }
    public List<NoticeDTO>searchSelectPage(SearchCondition sc){
        return dao.searchSelectPage(sc);
    }

    public int updateNotice(NoticeDTO dto){
        try {
            int result = dao.updateNotice(dto);
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
