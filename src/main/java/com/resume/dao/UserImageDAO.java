package com.resume.dao;

import com.resume.dto.UserImageDTO;
import com.resume.dto.AwsS3;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserImageDAO {
    void updateImage(AwsS3 dto);
    UserImageDTO findImageById(String userid);
}
