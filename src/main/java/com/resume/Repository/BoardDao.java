package com.resume.Repository;

import com.resume.dto.BoardDto;
import com.resume.dto.CommentDto;
import com.resume.dto.SearchCondition;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface BoardDao {
    int saveBoard(BoardDto dto);
    int deleteBoard(int num);
    BoardDto findByNum(int num);
    List<BoardDto> findAll(Map map);
    
    int searchResultCnt(SearchCondition sc);
    List<BoardDto> searchSelectPage(SearchCondition sc);
    int updateBoard(BoardDto dto);
    int insertComment(CommentDto dto);
    List<CommentDto> getComments(int boardno);
    int deleteComment(int commentno);
}
