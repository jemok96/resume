package com.resume.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
public class NoticeDto {
    private int num;
    private String userid;
    @NotBlank(message = "제목은 필수입니다.")
    private String title;
    @NotBlank(message = "내용을 입력해주세요")
    private String contents;
    private Date regdate;
    private Date up_date;
    private int hits;

    @Builder
    public NoticeDto(int num, String userid, String title, String contents, Date regdate, Date up_date, int hits) {
        this.num = num;
        this.userid = userid;
        this.title = title;
        this.contents = contents;
        this.regdate = regdate;
        this.up_date = up_date;
        this.hits = hits;
    }

    public NoticeDto() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoticeDto noticeDTO = (NoticeDto) o;
        return num == noticeDTO.num && hits == noticeDTO.hits && Objects.equals(userid, noticeDTO.userid) && Objects.equals(title, noticeDTO.title) && Objects.equals(contents, noticeDTO.contents) && Objects.equals(regdate, noticeDTO.regdate) && Objects.equals(up_date, noticeDTO.up_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, userid, title, contents, regdate, up_date, hits);
    }

    @Override
    public String toString() {
        return "NoticeDTO{" +
                "num=" + num +
                ", userid='" + userid + '\'' +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", regdate=" + regdate +
                ", up_date=" + up_date +
                ", hits=" + hits +
                '}';
    }
}
