package com.resume.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
public class ResumeDTO {
    private Integer resumeno;
    private String userid;
    @NotBlank(message = "제목은 필수입니다.")
    private String title;
    @NotBlank(message = "내용을 입력해주세요")
    @Size(max = 2000,message = "최대2000자입니다.")
    private String contents;
    private Date regdate;
    private Date up_date;

    @Builder
    public ResumeDTO(Integer resumeno, String userid,String title ,String contents, Date regdate, Date up_date) {
        this.resumeno = resumeno;
        this.userid = userid;
        this.title = title;
        this.contents = contents;
        this.regdate = regdate;
        this.up_date = up_date;
    }

    public ResumeDTO() {
    }

    @Override
    public String toString() {
        return "ResumeDTO{" +
                "resumeno=" + resumeno +
                ", userid='" + userid + '\'' +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", regdate=" + regdate +
                ", update=" + up_date +
                '}';
    }
}
