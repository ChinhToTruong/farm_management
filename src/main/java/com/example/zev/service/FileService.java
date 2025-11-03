package com.example.zev.service;

import com.example.zev.dto.response.uploadfile.GetFileResponse;
import com.example.zev.dto.response.uploadfile.UploadFileResponse;
import io.minio.*;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    @Value("${minio.bucket}")
    private String bucketName;

    private final MinioClient minioClient;


    public UploadFileResponse uploadFile(MultipartFile file) throws Exception {
        String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();
        String uploadDir = "http://minio:9001/browser";

        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .contentType(file.getContentType())
                        .build()
        );
        // ✅ Trả về đường dẫn tương ứng trong host
        String filePath = uploadDir + "/" + bucketName + "/" + fileName;

        return UploadFileResponse.builder()
                .fileName(fileName)
                .filePath(filePath)
                .fileType(file.getContentType())
                .build();
    }

    public InputStream downloadFile(String fileName) throws Exception {
        return minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .build()
        );
    }
    public GetFileResponse getFileAsBase64(String bucketName, String objectName) {
        try (InputStream stream = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .build()
        )) {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            byte[] data = new byte[8192];
            int bytesRead;

            while ((bytesRead = stream.read(data)) != -1) {
                buffer.write(data, 0, bytesRead);
            }

            String base64 = Base64.getEncoder().encodeToString(buffer.toByteArray());
            return new GetFileResponse(objectName, base64);

        } catch (Exception e) {
            throw new RuntimeException("Error reading file from MinIO: " + e.getMessage(), e);
        }
    }


    public void createBucketIfNotExists() throws Exception {
        boolean found = minioClient.bucketExists(
                BucketExistsArgs.builder().bucket(bucketName).build()
        );
        if (!found) {
            minioClient.makeBucket(
                    MakeBucketArgs.builder().bucket(bucketName).build()
            );
        }
    }
}
