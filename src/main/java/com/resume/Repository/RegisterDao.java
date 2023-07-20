package com.resume.Repository;

import com.resume.dto.RegisterDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface RegisterDao {

    int saveuser(RegisterDto user);

    int idcheck(String userid);

    RegisterDto userInfo(String sessionid);


    void saveImage(String userid);
}
