package com.resume.Repository;

import com.resume.dto.RegisterDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface RegisterDAO {

    int saveuser(RegisterDTO user);

    int idcheck(String userid);

    RegisterDTO userInfo(String sessionid);


    void saveImage(String userid);
}
