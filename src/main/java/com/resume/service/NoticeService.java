package com.resume.service;

import com.resume.Repository.NoticeDao;
import com.resume.dto.NoticeDto;
import com.resume.dto.SearchCondition;
import com.resume.exception.PostNotFountException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class NoticeService {
    private final CacheManager cacheManager;
    private final NoticeDao dao;

    public NoticeService(CacheManager cacheManager, NoticeDao dao) {
        this.cacheManager = cacheManager;
        this.dao = dao;
    }

    public int saveNotice(NoticeDto dto) {
        return dao.saveNotice(dto);
    }

    public int deleteNotice(int num){
        NoticeDto findNotice = dao.findByNum(num);
        if(findNotice == null){
            throw new PostNotFountException("존재하지 않는 게시글입니다.");
        }
        return dao.deleteNotice(num);
    }
    public NoticeDto findByNum(int num){
        NoticeDto findNotice = dao.findByNum(num);
        if(findNotice == null){
            throw new PostNotFountException("존재하지 않는 게시글입니다.");
        }
        return dao.findByNum(num);
    }


    public int searchResultCnt(SearchCondition sc){
        return dao.searchResultCnt(sc);
    }
    @Cacheable("postComments")
    public List<NoticeDto>searchSelectPage(SearchCondition sc){
        List<NoticeDto> result = dao.searchSelectPage(sc);
        return result;
    }

    public int updateNotice(NoticeDto dto){
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
