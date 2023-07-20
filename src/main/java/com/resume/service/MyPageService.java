package com.resume.service;

import com.resume.config.PasswordConfig;
import com.resume.Repository.LoginDao;
import com.resume.Repository.MyPageDao;
import com.resume.dto.UserDto;
import com.resume.dto.UserPwDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class MyPageService {
    private final MyPageDao manageMentDAO;
    private final LoginDao loginDAO;

    public MyPageService(MyPageDao dao, LoginDao loginDAO) {
        this.manageMentDAO = dao;
        this.loginDAO = loginDAO;
    }

    public Integer checkPw(UserDto dto){
        String encPw = loginDAO.findPw(dto.getUserid());
        String pw = dto.getPassword();
        boolean check = PasswordConfig.checkUserPw(pw, encPw);
        if(check){
            return 1;
        }
        return 400;
    }
    public int changePw(UserPwDto dto, String userId){
        String newPassword = dto.getNewPassword();
        String nowPassword = dto.getNowPassword(); //현재 비밀번호
        String passwordCheck = dto.getPasswordCheck();
        boolean check = PasswordConfig.checkUserPw(nowPassword,loginDAO.findPw(userId));
        log.info("check={}",check);
        int value = 0;
        //입력한 pw값과 db에서조회한 값이 같고 변경할 비밀번호가 서로 같을 경우
        if(check && newPassword.equals(passwordCheck) ){
            Map map = new HashMap();
            map.put("password",PasswordConfig.encryptPassword(passwordCheck));
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
    public int deleteUser(String userId,String password){

        boolean b = PasswordConfig.checkPassword(password, loginDAO.findPw(userId));
        log.info("b={}",b);

        if(b){
            return manageMentDAO.deleteUser(userId);
        }
        return 500;
    }

}
