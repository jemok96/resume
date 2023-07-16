package com.resume.Repository;

import com.resume.dto.BoardDTO;
import com.resume.dto.CommentDTO;
import com.resume.dto.NoticeDTO;
import com.resume.dto.SearchCondition;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface BoardDAO {
    int saveBoard(BoardDTO dto);
    int deleteBoard(int num);
    BoardDTO findByNum(int num);
    List<BoardDTO> findAll(Map map);
    
    int searchResultCnt(SearchCondition sc);
    List<BoardDTO> searchSelectPage(SearchCondition sc);
    int updateBoard(BoardDTO dto);
    int insertComment(CommentDTO dto);
    List<CommentDTO> getComments(int boardno);
}
