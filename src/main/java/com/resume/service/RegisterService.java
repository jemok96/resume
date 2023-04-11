package com.resume.service;

import com.resume.dao.RegisterDAO;
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
        int result =  dao.saveuser(user);
        return result;
    }

    public int idcheck(String userid) {
        return dao.idcheck(userid);
    }
}
