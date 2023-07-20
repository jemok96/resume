package com.resume.Repository;

import com.resume.dto.EmailCheckDto;
import com.resume.dto.LoginUserDto;
import com.resume.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface LoginDao {
    LoginUserDto checkUser(LoginUserDto user);
    EmailCheckDto checkEmail(EmailCheckDto email);
    List<UserDto> findIdByEmail(String email);
    String findPw(String userId);
}
