package com.resume.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class S3UploadServiceTest {
    @Autowired
    private S3UploadService service;

    @Test
    void uploadTest() throws Exception{
        String imgPath = service.getObjectUrl("upload/0710eb94-3d25-4c82-901d-62878dfed3e1KakaoTalk_20230423_201016500.jpg");
        log.info("path={}",imgPath);

    }

}