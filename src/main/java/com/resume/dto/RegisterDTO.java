package com.resume.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RegisterDTO {


    private String userid;

    private String password;

    private String email;

    private String gender;

    private String phone;

    private String birth;

    private String name;


    @Override
    public String toString() {
        return "LoginUserDTO{" +
                "userid='" + userid + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", birth='" + birth + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
