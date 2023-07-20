package com.resume.Repository;

import com.resume.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@Mapper
public interface MyPageDao {
    int checkPw(UserDto dto);
    String getPassword(String userId);
    int changePw(Map map);
    int deleteUser(String id);
}
