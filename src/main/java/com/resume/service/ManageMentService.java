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

    public Integer checkPw(UserDTO dto){
        String encPw = loginDAO.findPw(dto.getUserid());
        String pw = dto.getPassword();
        boolean check = PasswordConfig.checkUserPw(pw, encPw);
        if(check){
            return 1;
        }
        return 400;
    }
    public int changePw(UserPwDTO dto,String userId){
        String newPassword = dto.getNewPassword();
        String nowPassword = dto.getNowPassword(); //현재 비밀번호
        String passwordCheck = dto.getPasswordCheck();
        boolean check = PasswordConfig.checkUserPw(dto.getNowPassword(),loginDAO.findPw(userId));
        log.info("check={}",check);
        int value = 0;
        //입력한 pw값과 db에서조회한 값이 같고 변경할 비밀번호가 서로 같을 경우
        if(check && newPassword.equals(passwordCheck) ){
            Map map = new HashMap();
            map.put("password",dto.getPasswordCheck());
            map.put("userid",userId);
            value = manageMentDAO.changePw(map);
        }
        // 현재 비밀번호가 다를 경우 
        else if(!check){
            value= 200;
        }
        // 새 비밀번호와 새 비밀번호 확인이 다를경우
        else if(!newPassword.equals(passwordCheck)){
            value= 300;
        }
        return value;


    }
    public String getPassword(String userId){
        return manageMentDAO.getPassword(userId);

    }

}
