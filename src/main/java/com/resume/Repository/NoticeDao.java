package com.resume.Repository;

import com.resume.dto.NoticeDto;
import com.resume.dto.SearchCondition;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface NoticeDao {
    int saveNotice(NoticeDto dto);
    int deleteNotice(int num);
    NoticeDto findByNum(int num);
    List<NoticeDto> findAll(Map map);

    int searchResultCnt(SearchCondition sc);
    List<NoticeDto> searchSelectPage(SearchCondition sc);
    int updateNotice(NoticeDto dto);
}
