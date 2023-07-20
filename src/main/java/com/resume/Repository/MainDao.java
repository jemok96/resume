package com.resume.Repository;

import com.resume.dto.ExperienceDto;
import com.resume.dto.RegisterDto;
import com.resume.dto.UserInfoDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface MainDao {
    RegisterDto userInfo(String sessionid);

    UserInfoDto usersubinfo(String sessionid);

    void infoupdate(Map map);

    void infoinsert(String sessionid);

    List<ExperienceDto> experienceinfo(String sessionid);

    void experienceAdd(ExperienceDto experidto);

    void experienceDelete(String seqno1);

    ExperienceDto experienceInfo2(String seqno1);

    void experienceModify(ExperienceDto experidto);

    void skillSave(Map map);

    void skillDelete(Map map);

    List<String> selectSkill(String sessionId);

    List<String> selectSkillname(String sessionid);
}
