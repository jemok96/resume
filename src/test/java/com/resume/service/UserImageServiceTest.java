package com.resume.service;

import com.resume.dto.UserImageDTO;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class UserImageServiceTest {

    @Autowired
    UserImageService service;

    @Test
    void findImageById() throws Exception{
        //given
        String userid = "rudnf9605";
        //when
        UserImageDTO fingUser = service.findImageById(userid);
        //then
//        Assertions.assertThat(userid).isEqualTo(fingUser.getUserid());

    }
}