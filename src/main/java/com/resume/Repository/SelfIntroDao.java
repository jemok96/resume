package com.resume.Repository;

import com.resume.dto.SelfIntroDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SelfIntroDao {
    int insertResume(SelfIntroDto dto);
    List<SelfIntroDto> findResumeById(String userid);
    void deleteAll();
    int deleteOne(Integer rno);
    int updateResume(SelfIntroDto dto);
    SelfIntroDto selectByNo(Integer rno);


    SelfIntroDto testCode(SelfIntroDto dto);
}
