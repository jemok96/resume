package com.resume.service;

import com.resume.dto.NoticeDto;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;

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
        NoticeDto notice = NoticeDto.builder()
                .userid("admin")
                .title("안녕하세요")
                        .contents("공지사항입니다.").build();
        Assertions.assertThat(noticeService.saveNotice(notice) ==1);

    }
    @Test
    void cacheDataaAdd() throws Exception{
        //given
        NoticeDto[] dto = new NoticeDto[150000];
        for(int i =0; i<dto.length; i++){
            dto[i] = new NoticeDto();
            dto[i].setUserid("admin");
            dto[i].setTitle("test"+i);
            dto[i].setContents("testContents"+i);
            noticeService.saveNotice(dto[i]);
        }
    }

}