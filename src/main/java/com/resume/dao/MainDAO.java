package com.resume.dao;

import com.resume.dto.ExperienceDTO;
import com.resume.dto.RegisterDTO;
import com.resume.dto.UserInfoDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface MainDAO {
    RegisterDTO userInfo(String sessionid);

    UserInfoDTO usersubinfo(String sessionid);

    void infoupdate(Map map);

    void infoinsert(String sessionid);

    List<ExperienceDTO> experienceinfo(String sessionid);

    void experienceAdd(ExperienceDTO experidto);

    void experienceDelete(String seqno1);

    ExperienceDTO experienceInfo2(String seqno1);

    void experienceModify(ExperienceDTO experidto);

    void skillSave(Map map);

    void skillDelete(Map map);

    List<String> selectSkill(String sessionId);

    List<String> selectSkillname(String sessionid);
}
