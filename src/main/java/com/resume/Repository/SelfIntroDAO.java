package com.resume.Repository;

import com.resume.dto.SelfIntroDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SelfIntroDAO {
    int insertResume(SelfIntroDTO dto);
    List<SelfIntroDTO> findResumeById(String userid);
    void deleteAll();
    int deleteOne(Integer rno);
    int updateResume(SelfIntroDTO dto);
    SelfIntroDTO selectByNo(Integer rno);


    SelfIntroDTO testCode(SelfIntroDTO dto);
}
