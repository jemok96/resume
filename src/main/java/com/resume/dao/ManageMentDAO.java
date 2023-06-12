package com.resume.dao;

import com.resume.dto.UserDTO;
import com.resume.dto.UserPwDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@Mapper
public interface ManageMentDAO {
    int checkPw(UserDTO dto);
    String getPassword(String userId);
    int changePw(Map map);
}
