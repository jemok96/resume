package com.resume.dao;

import com.resume.dto.RegisterDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RegisterDAO {

    int saveuser(RegisterDTO user);

    int idcheck(String userid);

    RegisterDTO userInfo(String sessionid);


    void saveImage(String userid);
}
