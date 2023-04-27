package com.resume.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter
@Setter
public class UserDTO {
    private String userid;
    @NotEmpty
    private String password;
    private String email;
    private String gender;
    private String phone;
    private String birth;
    private String name;
    private Date regdate;
    private Date up_date;

    public UserDTO() {
    }

    @Builder
    public UserDTO(String userid, String password, String email, String gender, String phone, String birth, String name, Date regdate, Date up_date) {
        this.userid = userid;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
        this.birth = birth;
        this.name = name;
        this.regdate = regdate;
        this.up_date = up_date;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userid='" + userid + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", birth='" + birth + '\'' +
                ", name='" + name + '\'' +
                ", regdate=" + regdate +
                ", up_date=" + up_date +
                '}';
    }
}
