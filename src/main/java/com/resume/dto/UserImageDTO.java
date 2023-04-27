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
    private String keyvalue;
    private String url;
    private String realname;
    private Date regdate;
    private Date up_date;


    public UserImageDTO() {}

    @Builder
    public UserImageDTO(Integer imageno, String userid, String keyvalue, String url, String realname, Date regdate, Date up_date) {
        this.imageno = imageno;
        this.userid = userid;
        this.keyvalue = keyvalue;
        this.url = url;
        this.realname = realname;
        this.regdate = regdate;
        this.up_date = up_date;
    }

    @Override
    public String toString() {
        return "UserImageDTO{" +
                "imageno=" + imageno +
                ", userid='" + userid + '\'' +
                ", keyvalue='" + keyvalue + '\'' +
                ", url='" + url + '\'' +
                ", realname='" + realname + '\'' +
                ", regdate=" + regdate +
                ", up_date=" + up_date +
                '}';
    }
}
