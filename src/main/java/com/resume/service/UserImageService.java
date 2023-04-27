package com.resume.service;

import com.resume.dao.UserImageDAO;
import com.resume.dto.AwsS3;
import com.resume.dto.UserImageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserImageService {
    private UserImageDAO dao;

    public UserImageService(UserImageDAO dao) {
        this.dao = dao;
    }

    public void updateImage(AwsS3 dto){
        dao.updateImage(dto);
    }
    public UserImageDTO findImageById(String userid){
        return dao.findImageById(userid);
    }
}
