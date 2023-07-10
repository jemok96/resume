package com.resume.service;

import com.resume.config.PasswordConfig;
import com.resume.Repository.RegisterDAO;
import com.resume.dto.RegisterDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RegisterService {

    private final RegisterDAO dao;
    public RegisterService(RegisterDAO dao) {
        this.dao = dao;
    }

    public int saveuser(RegisterDTO user) {
        user.setPassword(PasswordConfig.encryptPassword(user.getPassword()));
        int result =  dao.saveuser(user);
        return result;
    }

    public int idcheck(String userid) {
        return dao.idcheck(userid);
    }

    public RegisterDTO userInfo(String sessionid) {
        return dao.userInfo(sessionid);
    }


    public void saveImage(String userid) {
        dao.saveImage(userid);
    }
}
