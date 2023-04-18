package com.resume.dao;

import com.resume.dto.RegisterDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MainDAO {
    RegisterDTO userInfo(String sessionid);
}
