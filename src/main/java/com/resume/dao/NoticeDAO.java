package com.resume.dao;

import com.resume.dto.NoticeDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface NoticeDAO {
    int saveNotice(NoticeDTO dto);
    int deleteNotice(int num);
    int selectNoticeOne(NoticeDTO dto);
}
