package com.resume.service;

import com.resume.dao.MainDAO;
import com.resume.dto.ExperienceDTO;
import com.resume.dto.RegisterDTO;
import com.resume.dto.UserInfoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    public UserInfoDTO usersubinfo(String sessionid) {
        return dao.usersubinfo(sessionid);
    }

    public void infoupdate(Map map) {
         dao.infoupdate(map);
    }

    public void infoinsert(String sessionid) {
         dao.infoinsert(sessionid);
    }

    public List<ExperienceDTO> experienceinfo(String sessionid) {
       return dao.experienceinfo(sessionid);
    }

    public void experienceAdd(ExperienceDTO experidto) {
        dao.experienceAdd(experidto);
    }
}
