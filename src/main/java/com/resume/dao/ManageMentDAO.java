package com.resume.dao;

import com.resume.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ManageMentDAO {
    int checkPw(UserDTO dto);
    UserDTO findById(String id);

}
