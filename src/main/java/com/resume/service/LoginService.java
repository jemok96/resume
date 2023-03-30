package com.resume.service;

import com.resume.dao.LoginDAO;
import com.resume.dto.LoginUserDTO;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final LoginDAO dao;

    public LoginService(LoginDAO dao) {
        this.dao = dao;
        System.out.println("dao = " + dao);
    }
    public LoginUserDTO checkUser(String userid){
        return dao.checkUser(userid);
    }
}
