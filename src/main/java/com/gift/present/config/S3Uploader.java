package com.gift.present.config;


import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import jdk.internal.org.jline.utils.Log;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class S3Uploader {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    public String bucket;

    public String upload(MultipartFile multipartFile, String dirName) throws IOException {
        File uploadFile = convert(multipartFile)
                .orElseThrow(()-> new IllegalArgumentException("error : MultipartFile -> File convert fail"));
        System.out.println("uploadfile: "+uploadFile);
        System.out.println("dirName"+dirName);
        return upload(uploadFile, dirName);
    }

    // s3에서 파일 삭제
    public void deleteFromS3(String source) {
        amazonS3Client.deleteObject(new DeleteObjectRequest(bucket, source));
    }

    // S3로 파일 업로드하기
    private String upload(File uploadFile, String dirName) {
///        String fileName =  UUID.randomUUID() + uploadFile.getName();   // S3에 저장된 파일 이름

        String fileName = dirName + "/" + UUID.randomUUID() + uploadFile.getName();   // S3에 저장된 파일 이름
        String uploadImageUrl = putS3(uploadFile, fileName); // s3로 업로드
        System.out.println("gg filename: "+fileName);
        System.out.println("gg uploadFile: "+uploadFile);
        System.out.println("uploadimgurl : "+uploadImageUrl);
        removeNewFile(uploadFile);
        return uploadImageUrl;
    }


    // S3로 업로드
    private String putS3(File uploadFile, String fileName) {
System.out.println("버킷:"+bucket);
        System.out.println("파일네임:"+fileName);
        System.out.println("업로드파일:"+uploadFile);
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));

        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    // 로컬에 저장된 이미지 지우기
    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            System.out.println("delete Success");
            return;
        }
        System.out.println("delete failed");

    }


    // 로컬에 파일 업로드 하기
    private Optional<File> convert(MultipartFile file) throws IOException {

        System.out.println("이미지 경로!!!!!!!!!!!!!!!!!!!!!!!! : " + System.getProperty("user.dir") + "/" + file.getOriginalFilename());

        File convertFile = new File(System.getProperty("user.dir") + "/" + file.getOriginalFilename());

        if (convertFile.createNewFile()) { // 바로 위에서 지정한 경로에 File이 생성됨 (경로가 잘못되었다면 생성 불가능)
            try (FileOutputStream fos = new FileOutputStream(convertFile)) { // FileOutputStream 데이터를 파일에 바이트 스트림으로 저장하기 위함
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }
        System.out.println("여길 왜와!!!!!!!!!!!!!!!!!");
        return Optional.empty();
    }
}








