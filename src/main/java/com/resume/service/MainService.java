package com.resume.service;

import com.resume.Repository.MainDao;
import com.resume.dto.ExperienceDto;
import com.resume.dto.RegisterDto;
import com.resume.dto.UserInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class MainService {

    private final MainDao dao;

    public MainService(MainDao dao) {
        this.dao = dao;
    }


    public RegisterDto userInfo(String sessionid) {
        return dao.userInfo(sessionid);
    }

    public UserInfoDto usersubinfo(String sessionid) {
        return dao.usersubinfo(sessionid);
    }

    public void infoupdate(Map map) {
         dao.infoupdate(map);
    }

    public void infoinsert(String sessionid) {
         dao.infoinsert(sessionid);
    }

    public List<ExperienceDto> experienceinfo(String sessionid) {
       return dao.experienceinfo(sessionid);
    }

    public void experienceAdd(ExperienceDto experidto) {
        dao.experienceAdd(experidto);
    }

    public void experienceDelete(String seqno1) {
        dao.experienceDelete(seqno1);
    }

    public ExperienceDto experienceInfo2(String seqno1) {
        return dao.experienceInfo2(seqno1);
    }

    public void experienceModify(ExperienceDto experidto) {
         dao.experienceModify(experidto);
    }

    public void skillSave(Map map) {
        dao.skillSave(map);
    }

    public void skillDelete(Map map) {
        dao.skillDelete(map);
    }

    public List<String> selectSkill(String sessionId) {
        return dao.selectSkill(sessionId);
    }

    public List<String> selectSkillname(String sessionid) {
        return dao.selectSkillname(sessionid);
    }
}
