package com.resume.dao;

import com.resume.dto.ResumeDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ResumeDAO {
    int insertResume(ResumeDTO dto);
    List<ResumeDTO> findResumeById(String userid);
    void deleteAll();
    int deleteOne(Integer rno);
    int updateResume(ResumeDTO dto);
    ResumeDTO selectByNo(Integer rno);


    ResumeDTO testCode(ResumeDTO dto);
}
