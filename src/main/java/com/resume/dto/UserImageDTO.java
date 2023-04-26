package com.resume.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserImageDTO {
    private Integer imageno;
    private String userid;
    private String uuid;
    private String path;
    private String realname;
    private Date regdate;
    private Date up_date;


    public UserImageDTO() {}

    @Builder
    public UserImageDTO(Integer imageno, String userid, String uuid, String path, String realname, Date regdate, Date up_date) {
        this.imageno = imageno;
        this.userid = userid;
        this.uuid = uuid;
        this.path = path;
        this.realname = realname;
        this.regdate = regdate;
        this.up_date = up_date;
    }

}
