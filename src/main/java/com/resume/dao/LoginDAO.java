package com.resume.dao;

import com.resume.dto.LoginUserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface LoginDAO {
    LoginUserDTO checkUser(String userid);

}
