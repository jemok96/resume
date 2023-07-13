package com.resume.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
public class BoardDTO {
    private Integer boardno;
    private String userid;
    @NotBlank
    private String title;
    @NotBlank
    private String contents;
    private Date regdate;
    private Date up_date;
    private Integer hits;
    public BoardDTO(){}

    @Builder
    public BoardDTO(Integer boardno, String userid, String title, String contents, Date regdate, Date up_date, Integer hits) {
        this.boardno = boardno;
        this.userid = userid;
        this.title = title;
        this.contents = contents;
        this.regdate = regdate;
        this.up_date = up_date;
        this.hits = hits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardDTO boardDTO = (BoardDTO) o;
        return Objects.equals(boardno, boardDTO.boardno) && Objects.equals(userid, boardDTO.userid) && Objects.equals(title, boardDTO.title) && Objects.equals(contents, boardDTO.contents) && Objects.equals(regdate, boardDTO.regdate) && Objects.equals(up_date, boardDTO.up_date) && Objects.equals(hits, boardDTO.hits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(boardno, userid, title, contents, regdate, up_date, hits);
    }

    @Override
    public String toString() {
        return "BoardDTO{" +
                "boardno=" + boardno +
                ", userid='" + userid + '\'' +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", regdate=" + regdate +
                ", up_date=" + up_date +
                ", hits=" + hits +
                '}';
    }
}
