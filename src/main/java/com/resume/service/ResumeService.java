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
}
