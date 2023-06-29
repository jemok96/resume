package com.resume.dao;

import com.resume.dto.EmailCheckDTO;
import com.resume.dto.LoginUserDTO;
import com.resume.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface LoginDAO {
    LoginUserDTO checkUser(LoginUserDTO user);
    EmailCheckDTO checkEmail(EmailCheckDTO email);
    UserDTO findIdByEmail(String email);
}
