package com.resume.Repository;

import com.resume.dto.EmailCheckDTO;
import com.resume.dto.LoginUserDTO;
import com.resume.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface LoginDAO {
    LoginUserDTO checkUser(LoginUserDTO user);
    EmailCheckDTO checkEmail(EmailCheckDTO email);
    List<UserDTO> findIdByEmail(String email);
    String findPw(String userId);
}
