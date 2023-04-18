package com.resume.service;

import com.resume.dao.MainDAO;
import com.resume.dto.RegisterDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MainService {

    private final MainDAO dao;

    public MainService(MainDAO dao) {
        this.dao = dao;
    }


    public RegisterDTO userInfo(String sessionid) {
        return dao.userInfo(sessionid);
    }
}
