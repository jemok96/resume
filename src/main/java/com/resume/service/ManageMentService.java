package com.resume.service;

import com.resume.dao.ManageMentDAO;
import com.resume.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ManageMentService {
    private final ManageMentDAO dao;

    public ManageMentService(ManageMentDAO dao) {
        this.dao = dao;
    }

    public int checkPw(UserDTO dto){
        return dao.checkPw(dto);
    }
    public UserDTO findById(String id,String sessionId){
        UserDTO byId = dao.findById(id);
        if(byId.getUserid().equals(sessionId)){
            return byId;
        }
        else{
            return null;
        }
    }
}
