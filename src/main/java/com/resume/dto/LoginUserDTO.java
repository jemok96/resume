package com.resume.dto;

import lombok.Getter;
import lombok.Setter;


import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginUserDTO {
    @NotBlank(message = "아이디를 입력하세요")
    private String userid;
    @NotBlank(message = "비밀번호를 입력하세요")
    private String password;

    public LoginUserDTO() {
    }

    @Override
    public String toString() {
        return "LoginUserDTO{" +
                "userid='" + userid + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
