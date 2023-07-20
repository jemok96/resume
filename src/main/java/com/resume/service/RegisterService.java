package com.resume.service;

import com.resume.config.PasswordConfig;
import com.resume.Repository.RegisterDao;
import com.resume.dto.RegisterDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RegisterService {

    private final RegisterDao dao;
    public RegisterService(RegisterDao dao) {
        this.dao = dao;
    }

    public int saveuser(RegisterDto user) {
        user.setPassword(PasswordConfig.encryptPassword(user.getPassword()));
        int result =  dao.saveuser(user);
        return result;
    }

    public int idcheck(String userid) {
        return dao.idcheck(userid);
    }

    public RegisterDto userInfo(String sessionid) {
        return dao.userInfo(sessionid);
    }


    public void saveImage(String userid) {
        dao.saveImage(userid);
    }
}
