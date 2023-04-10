package com.resume.service;

import com.resume.dao.LoginDAO;
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

    public String checkUser(LoginUserDTO user) {
        LoginUserDTO findUser = dao.checkUser(user.getUserid());
        if(findUser ==null){
            return "null";
        }
        else if (findUser.getUserid().equals(user.getUserid()) && findUser.getPassword().equals(user.getPassword())) {
            return "success";
        }
        return "fail";
    }
}

