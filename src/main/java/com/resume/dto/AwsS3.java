package com.resume.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AwsS3 {
    private String keyvalue;
    private String url;

    public AwsS3() {

    }

    @Builder
    public AwsS3(String keyvalue, String url) {
        this.keyvalue = keyvalue;
        this.url = url;
    }

    @Override
    public String toString() {
        return "AwsS3{" +
                "key='" + keyvalue + '\'' +
                ", path='" + url + '\'' +
                '}';
    }
}
