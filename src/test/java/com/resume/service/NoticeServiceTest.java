package com.resume.service;

import com.resume.dto.NoticeDTO;
import com.resume.dto.SearchCondition;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class NoticeServiceTest {

    @Autowired
    private NoticeService noticeService;
    @Autowired
    private CacheManager cacheManager;
    @Test
    void crudTest() throws Exception{
        //saveTest
        NoticeDTO notice = NoticeDTO.builder()
                .userid("admin")
                .title("안녕하세요")
                        .contents("공지사항입니다.").build();
        Assertions.assertThat(noticeService.saveNotice(notice) ==1);

    }
    @Test
    void cacheDataaAdd() throws Exception{
        //given
        NoticeDTO[] dto = new NoticeDTO[150000];
        for(int i =0; i<dto.length; i++){
            dto[i] = new NoticeDTO();
            dto[i].setUserid("admin");
            dto[i].setTitle("test"+i);
            dto[i].setContents("testContents"+i);
            noticeService.saveNotice(dto[i]);
        }
    }

}