package com.resume.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExperienceDTO {

    private String userid;

    private String job;

    private String company;

    private String contents;

    private String start_dt;

    private String end_dt;

    private String experi_no;

    @Override
    public String toString() {
        return "ExperienceDTO{" +
                "userid='" + userid + '\'' +
                ", job='" + job + '\'' +
                ", company='" + company + '\'' +
                ", contents='" + contents + '\'' +
                ", start_dt='" + start_dt + '\'' +
                ", end_dt='" + end_dt + '\'' +
                ", experi_no='" + experi_no + '\'' +
                '}';
    }
}
