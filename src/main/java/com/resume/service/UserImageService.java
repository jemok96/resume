package com.resume.service;

import com.resume.Repository.UserImageDao;
import com.resume.dto.AwsS3Dto;
import com.resume.dto.UserImageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserImageService {
    private UserImageDao dao;

    public UserImageService(UserImageDao dao) {
        this.dao = dao;
    }

    public void updateImage(AwsS3Dto dto){
        dao.updateImage(dto);
    }
    public UserImageDto findImageById(String userid){
        return dao.findImageById(userid);
    }
}
