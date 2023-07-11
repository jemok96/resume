package com.resume.Repository;

import com.resume.dto.NoticeDTO;
import com.resume.dto.SearchCondition;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface NoticeDAO {
    int saveNotice(NoticeDTO dto);
    int deleteNotice(int num);
    NoticeDTO findByNum(int num);
    List<NoticeDTO> findAll(Map map);

    int searchResultCnt(SearchCondition sc);
    List<NoticeDTO> searchSelectPage(SearchCondition sc);
    int updateNotice(NoticeDTO dto);
}
