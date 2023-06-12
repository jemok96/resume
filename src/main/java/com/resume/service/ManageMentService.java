package com.resume.service;

import com.resume.dao.ManageMentDAO;
import com.resume.dto.UserDTO;
import com.resume.dto.UserPwDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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
    public int changePw(UserPwDTO dto,String userId){

        //현재 비밀번호와 유저session id로 가져온 비밀번호가 다르면 에러
        if(dto.getNowPassword().equals(getPassword(userId)) && dto.getNewPassword().equals(dto.getPasswordCheck()) ){
            Map map = new HashMap();
            map.put("password",dto.getPasswordCheck());
            map.put("userid",userId);
            return dao.changePw(map);
        }
        if(!dto.getNowPassword().equals(getPassword(userId))){
            return 200;
        }
        // 새 비밀번호와 새 비밀번호 확인이 다르면 에러
        else if(!dto.getNewPassword().equals(dto.getPasswordCheck())){
            return 300;
        }
        return 0;


    }
    public String getPassword(String userId){
        return dao.getPassword(userId);

    }

}
