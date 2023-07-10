package com.resume.Repository;

import com.resume.dto.UserDTO;
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
