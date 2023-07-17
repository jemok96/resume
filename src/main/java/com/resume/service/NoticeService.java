package com.resume.service;

import com.resume.Repository.NoticeDAO;
import com.resume.dto.NoticeDTO;
import com.resume.dto.SearchCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class NoticeService {
    private final CacheManager cacheManager;
    private final NoticeDAO dao;

    public NoticeService(CacheManager cacheManager, NoticeDAO dao) {
        this.cacheManager = cacheManager;
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
    @Cacheable("postComments")
    public List<NoticeDTO>searchSelectPage(SearchCondition sc){
        List<NoticeDTO> result = dao.searchSelectPage(sc);
        log.info("searchSelectPage 호출");
        return result;
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
