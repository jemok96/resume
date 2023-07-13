package com.resume.Repository;

import com.resume.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@Mapper
public interface MyPageDAO {
    int checkPw(UserDTO dto);
    String getPassword(String userId);
    int changePw(Map map);
    int deleteUser(String id);
}
