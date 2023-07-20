package com.resume.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class AwsS3Dto {
    private String keyvalue;
    private String url;
    private String userId;
    public AwsS3Dto() {

    }

    @Builder
    public AwsS3Dto(String keyvalue, String url, String userId) {
        this.keyvalue = keyvalue;
        this.url = url;
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AwsS3Dto awsS3 = (AwsS3Dto) o;
        return Objects.equals(keyvalue, awsS3.keyvalue) && Objects.equals(url, awsS3.url) && Objects.equals(userId, awsS3.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyvalue, url, userId);
    }

    @Override
    public String toString() {
        return "AwsS3{" +
                "keyvalue='" + keyvalue + '\'' +
                ", url='" + url + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
