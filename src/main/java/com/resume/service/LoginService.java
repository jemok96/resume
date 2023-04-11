package com.resume.service;

import com.resume.dao.LoginDAO;
import com.resume.dto.EmailCheckDTO;
import com.resume.dto.LoginUserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoginService {
    private final LoginDAO dao;

    public LoginService(LoginDAO dao) {
        this.dao = dao;
    }

    public Boolean checkUser(LoginUserDTO user) {
        LoginUserDTO findUser = dao.checkUser(user);
        log.info("findUser = {}",findUser);
        if(findUser == null) {
            return false;
        }
        else if (findUser.getUserid().equals(user.getUserid()) && findUser.getPassword().equals(user.getPassword())){
            return true;
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
}

