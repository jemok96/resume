package com.resume.service;

import com.resume.config.PasswordConfig;
import com.resume.dao.LoginDAO;
import com.resume.dto.EmailCheckDTO;
import com.resume.dto.LoginUserDTO;
import com.resume.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.springframework.stereotype.Service;

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
        try {
            boolean passwordMatch = PasswordConfig.checkPassword(pw, storedEncryptedPassword);
            log.info("pw={}", pw);
            log.info("passwordMatch={}", passwordMatch);

            if (passwordMatch) {
                return true;
            }
        } catch (EncryptionOperationNotPossibleException ex) {
            // 암호화된 비밀번호와 입력된 비밀번호가 일치하지 않는 경우
            return false;
        }

        return false;
    }



    public Boolean CheckEmail(EmailCheckDTO email){
        EmailCheckDTO emailCheckDTO = dao.checkEmail(email);
        if(emailCheckDTO != null)
            return true;
        else
            return false;

    }
    public UserDTO findIdByEmail(String email){
        return dao.findIdByEmail(email);
    }
}

