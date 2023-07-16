package com.resume.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class CommentDTO {
    private Integer commentno;
    private Integer boardno;
    private String writer;
    private String contents;
    public CommentDTO(){}


    @Builder
    public CommentDTO(Integer commentno, Integer boardno, String writer, String contents) {
        this.commentno = commentno;
        this.boardno = boardno;
        this.writer = writer;
        this.contents = contents;
    }


    @Override
    public String toString() {
        return "CommentDTO{" +
                "commentno=" + commentno +
                ", boardno=" + boardno +
                ", writer='" + writer + '\'' +
                ", contents='" + contents + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentDTO that = (CommentDTO) o;
        return Objects.equals(commentno, that.commentno) && Objects.equals(boardno, that.boardno) && Objects.equals(writer, that.writer) && Objects.equals(contents, that.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentno, boardno, writer, contents);
    }
}
