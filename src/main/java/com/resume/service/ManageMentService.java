package com.resume.service;

import com.resume.config.PasswordConfig;
import com.resume.dao.LoginDAO;
import com.resume.dao.ManageMentDAO;
import com.resume.dto.UserDTO;
import com.resume.dto.UserPwDTO;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class ManageMentService {
    private final ManageMentDAO manageMentDAO;
    private final LoginDAO loginDAO;

    public ManageMentService(ManageMentDAO dao, LoginDAO loginDAO) {
        this.manageMentDAO = dao;
        this.loginDAO = loginDAO;
    }

    public boolean checkPw(UserDTO dto){
        String encPw = loginDAO.findPw(dto.getUserid());
        String pw = dto.getPassword();
        return PasswordConfig.checkUserPw(pw, encPw);
    }
    public int changePw(UserPwDTO dto,String userId){

        //현재 비밀번호와 유저session id로 가져온 비밀번호가 다르면 에러
        int value = 0;
        if(dto.getNowPassword().equals(getPassword(userId)) && dto.getNewPassword().equals(dto.getPasswordCheck()) ){
            Map map = new HashMap();
            map.put("password",dto.getPasswordCheck());
            map.put("userid",userId);
            value = manageMentDAO.changePw(map);
        }
        else if(!dto.getNowPassword().equals(getPassword(userId))){
            value= 200;
        }
        // 새 비밀번호와 새 비밀번호 확인이 다르면 에러
        else if(!dto.getNewPassword().equals(dto.getPasswordCheck())){
            value= 300;
        }
        return value;


    }
    public String getPassword(String userId){
        return manageMentDAO.getPassword(userId);

    }

}
