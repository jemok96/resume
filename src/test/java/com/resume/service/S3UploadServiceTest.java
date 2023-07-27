package com.resume.service;

import com.resume.dto.AwsS3Dto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class S3UploadServiceTest {

    @Autowired
    S3UploadService service;

}