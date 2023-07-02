package com.resume.service;

import com.resume.dao.NoticeDAO;
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
    public int selectNoticeOne(NoticeDTO dto){
        return dao.selectNoticeOne(dto);
    }

    public List<NoticeDTO> findAll(Map map){
        return dao.findAll(map);
    }
    public int noticeCount() {
        return dao.noticeCount();
    }
    public int searchResultCnt(SearchCondition sc){
        return dao.searchResultCnt(sc);
    }
    public List<NoticeDTO>searchSelectPage(SearchCondition sc){
        return dao.searchSelectPage(sc);
    }
}
