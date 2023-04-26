package com.resume.dao;

import com.resume.dto.ExperienceDTO;
import com.resume.dto.RegisterDTO;
import com.resume.dto.UserInfoDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@Mapper
public interface MainDAO {
    RegisterDTO userInfo(String sessionid);

    UserInfoDTO usersubinfo(String sessionid);

    void infoupdate(Map map);

    void infoinsert(String sessionid);

    ExperienceDTO experienceinfo(String sessionid);
}
