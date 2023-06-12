package com.resume.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Setter
@Getter
public class UserPwDTO {
    @NotEmpty
    private String nowPassword;
    @NotEmpty
    private String newPassword;
    @NotEmpty
    private String passwordCheck;

    public UserPwDTO(){}

    @Builder
    public UserPwDTO(String nowPassword, String newPassword, String passwordCheck) {
        this.nowPassword = nowPassword;
        this.newPassword = newPassword;
        this.passwordCheck = passwordCheck;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPwDTO userPwDTO = (UserPwDTO) o;
        return Objects.equals(nowPassword, userPwDTO.nowPassword) && Objects.equals(newPassword, userPwDTO.newPassword) && Objects.equals(passwordCheck, userPwDTO.passwordCheck);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nowPassword, newPassword, passwordCheck);
    }

    @Override
    public String toString() {
        return "UserPwDTO{" +
                "nowPassword='" + nowPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", passwordCheck='" + passwordCheck + '\'' +
                '}';
    }
}
