package com.resume.service;

import com.resume.dao.ResumeDAO;
import com.resume.dto.ResumeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ResumeService {
    private final ResumeDAO dao;

    public ResumeService(ResumeDAO dao) {
        this.dao = dao;
    }

    public int insertResume(ResumeDTO dto){
        return dao.insertResume(dto);
    }
    public List<ResumeDTO> findResumeById(String userid){
        return dao.findResumeById(userid);
    }
    public void deleteAll(){
        dao.deleteAll();
    }
    public int updateResume(ResumeDTO dto){
        return dao.updateResume(dto);
    }
    public int deleteOne(Integer rno){
        return dao.deleteOne(rno);
    }
    public ResumeDTO selectByNo(Integer rno){
        return dao.selectByNo(rno);
    }


    public ResumeDTO testCode(ResumeDTO dto){
        return dao.testCode(dto);
    }
}
