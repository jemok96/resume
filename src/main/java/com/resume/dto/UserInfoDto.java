package com.resume.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoDto {
    private String userid;

    private String introduction;

    private String giturl;

    private String blogurl;

    @Override
    public String toString() {
        return "UserInfoDTO{" +
                "userid='" + userid + '\'' +
                ", introduction='" + introduction + '\'' +
                ", giturl='" + giturl + '\'' +
                ", blogurl='" + blogurl + '\'' +
                '}';
    }
}
