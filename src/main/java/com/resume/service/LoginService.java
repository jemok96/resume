package com.resume.service;

import com.resume.config.PasswordConfig;
import com.resume.Repository.LoginDao;
import com.resume.dto.EmailCheckDto;
import com.resume.dto.LoginUserDto;
import com.resume.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LoginService {
    private final LoginDao dao;

    public LoginService(LoginDao dao) {
        this.dao = dao;
    }

    public String findPw(String userId){
        return dao.findPw(userId);
    }
    public Boolean checkUser(LoginUserDto user) {
        log.info("dao={}",dao.getClass());
        String userId = user.getUserid(); //사용자가 입력한 아이디
        String storedEncryptedPassword = findPw(userId); // 사용자가 입력한 아이디로 찾은 암호화값

        String pw = user.getPassword();// 사용자가 입력한 pw
        return PasswordConfig.checkUserPw(pw, storedEncryptedPassword);
    }



    public Boolean CheckEmail(EmailCheckDto email){
        EmailCheckDto emailCheckDTO = dao.checkEmail(email);
        if(emailCheckDTO != null)
            return true;
        else
            return false;

    }
    public List<UserDto> findIdByEmail(String email){
        return dao.findIdByEmail(email);
    }
}

