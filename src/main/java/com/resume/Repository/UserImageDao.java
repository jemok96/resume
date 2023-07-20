package com.resume.Repository;

import com.resume.dto.UserImageDto;
import com.resume.dto.AwsS3Dto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserImageDao {
    void updateImage(AwsS3Dto dto);
    UserImageDto findImageById(String userid);
}
