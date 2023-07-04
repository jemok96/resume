package com.resume.service;

import com.resume.dto.NoticeDTO;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class NoticeServiceTest {

    @Autowired
    private NoticeService noticeService;
    @Test
    void crudTest() throws Exception{
        //saveTest
        NoticeDTO notice = NoticeDTO.builder()
                .userid("admin")
                .title("안녕하세요")
                        .contents("공지사항입니다.").build();
        Assertions.assertThat(noticeService.saveNotice(notice) ==1);
        //deleteTest
//        int num = noticeService.selectNoticeOne(notice);
//        log.info("num={}",num);
//        Assertions.assertThat(noticeService.deleteNotice(num)==1);
    }
    @Test
    void pagingCountTest() throws Exception{
        //given
        NoticeDTO[] dto = new NoticeDTO[110];
        for(int i =0; i<dto.length; i++){
            dto[i] = new NoticeDTO();
            dto[i].setUserid("admin");
            dto[i].setTitle("test"+i);
            dto[i].setContents("testContents"+i);
            noticeService.saveNotice(dto[i]);
        }
        //when

        //then

    }
}