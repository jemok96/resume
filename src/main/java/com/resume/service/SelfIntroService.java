package com.resume.service;

import com.resume.Repository.SelfIntroDAO;
import com.resume.dto.SelfIntroDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SelfIntroService {
    private final SelfIntroDAO dao;

    public SelfIntroService(SelfIntroDAO dao) {
        this.dao = dao;
    }

    public int insertResume(SelfIntroDTO dto){
        return dao.insertResume(dto);
    }
    public List<SelfIntroDTO> findResumeById(String userid){
        return dao.findResumeById(userid);
    }
    public void deleteAll(){
        dao.deleteAll();
    }
    public int updateResume(SelfIntroDTO dto){
        try {
            int result = dao.updateResume(dto);
            if (result == 1) {
                return 200;
            } else {
                return 500;
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to update resume", e);
        }
    }
    public int deleteOne(Integer rno){
        return dao.deleteOne(rno);
    }
    public SelfIntroDTO selectByNo(Integer rno){
        return dao.selectByNo(rno);
    }


    public SelfIntroDTO testCode(SelfIntroDTO dto){
        return dao.testCode(dto);
    }
}
