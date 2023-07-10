package com.resume.service;

import com.resume.config.PasswordConfig;
import com.resume.Repository.LoginDAO;
import com.resume.dto.EmailCheckDTO;
import com.resume.dto.LoginUserDTO;
import com.resume.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LoginService {
    private final LoginDAO dao;

    public LoginService(LoginDAO dao) {
        this.dao = dao;
    }

    public String findPw(String userId){
        return dao.findPw(userId);
    }
    public Boolean checkUser(LoginUserDTO user) {
        String userId = user.getUserid(); //사용자가 입력한 아이디
        String storedEncryptedPassword = findPw(userId); // 사용자가 입력한 아이디로 찾은 암호화값

        String pw = user.getPassword();// 사용자가 입력한 pw
        return PasswordConfig.checkUserPw(pw, storedEncryptedPassword);
    }



    public Boolean CheckEmail(EmailCheckDTO email){
        EmailCheckDTO emailCheckDTO = dao.checkEmail(email);
        if(emailCheckDTO != null)
            return true;
        else
            return false;

    }
    public List<UserDTO> findIdByEmail(String email){
        return dao.findIdByEmail(email);
    }
}

