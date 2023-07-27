package com.resume.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.resume.dto.AwsS3Dto;
import com.resume.dto.UserImageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class S3UploadService {
    private final UserImageService userImageService;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;

    public S3UploadService(UserImageService userImageService, AmazonS3 amazonS3) {
        this.userImageService = userImageService;
        this.amazonS3 = amazonS3;
    }

    public String getObjectUrl(String path) {
        return amazonS3.getUrl(bucket, path).toString();
    }

    public AwsS3Dto upload(MultipartFile multipartFile, String dirName, String userId) throws IOException {
        return upload(multipartFile.getInputStream(), dirName, userId, multipartFile.getOriginalFilename());
    }

    @Transactional
    public AwsS3Dto upload(InputStream inputStream, String dirName, String userId, String originalFilename) {
        String key = randomFileName(dirName, originalFilename);
        String path = putS3(inputStream, key);

        UserImageDto imageById = userImageService.findImageById(userId);
        String keyValue = imageById.getKeyvalue();
        // 기본이미지 즉,처음 변경할 때는 그냥 변경만하고  기본이미지가 아닐경우는 삭제하고 변경
        if (!imageById.getKeyvalue().equals("userImage/default_imaige.png")) {
            remove(keyValue);
        }
        return AwsS3Dto.builder().keyvalue(key).url(path).build();


    }

    public void remove(String keyValue) {
        if (!amazonS3.doesObjectExist(bucket, keyValue)) {
            throw new AmazonS3Exception("Object " + keyValue + " does not exist!");
        }
        amazonS3.deleteObject(bucket, keyValue);
    }

    private String randomFileName(String dirName, String originalFilename) {
        return dirName + "/" + UUID.randomUUID() + originalFilename;
    }

    private String putS3(InputStream inputStream, String fileName) {
        amazonS3.putObject(new PutObjectRequest(bucket, fileName, inputStream, null)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        return getS3(bucket, fileName);
    }

    private String getS3(String bucket, String fileName) {
        return amazonS3.getUrl(bucket, fileName).toString();
    }

}
