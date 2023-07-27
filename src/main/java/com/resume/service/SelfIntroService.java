package com.resume.service;

import com.resume.Repository.SelfIntroDao;
import com.resume.dto.SelfIntroDto;
import com.resume.exception.PostAccessDenyException;
import com.resume.exception.PostNotFountException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SelfIntroService {
    private final SelfIntroDao dao;

    public SelfIntroService(SelfIntroDao dao) {
        this.dao = dao;
    }

    public int insertResume(SelfIntroDto dto){
        return dao.insertResume(dto);
    }
    public List<SelfIntroDto> findResumeById(String userid){
        return dao.findResumeById(userid);
    }
    public void deleteAll(){
        dao.deleteAll();
    }
    public int updateResume(SelfIntroDto dto){
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
    public SelfIntroDto selectByNo(Integer rno,String userId){
        String resumeById = dao.findWriterUser(rno);

        if(resumeById == null){
            log.info("존재X");
            throw new PostNotFountException("존재하지 않는 게시글입니다.");
        }
        else if(!userId.equals(resumeById)){
            log.info("접근 예외");
            throw new PostAccessDenyException("접근 불가 합니다.");
        }


        log.info(resumeById);
        return dao.selectByNo(rno);
    }


    public String findWriterUser(Integer rno){
        return dao.findWriterUser(rno);

    }






    public SelfIntroDto testCode(SelfIntroDto dto){

        return dao.testCode(dto);
    }
}
