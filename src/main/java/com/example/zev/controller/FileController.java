package com.example.zev.controller;

import com.example.zev.dto.response.ResponseData;
import com.example.zev.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService minioService;


    @PostMapping("/upload")
    public ResponseData<?> upload(@RequestParam("file") MultipartFile file) throws Exception {
        return ResponseData.builder().data(minioService.uploadFile(file)).build();
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<?> download(@PathVariable("fileName") String fileName) throws Exception {
        var stream = minioService.downloadFile(fileName);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(stream.readAllBytes());
    }

    @GetMapping("/create-bucket")
    public void createBucket() throws Exception {
        minioService.createBucketIfNotExists();
    }

    @GetMapping("/get-file/{bucketName}/{fileName}")
    public ResponseData<?> getFile(@PathVariable("bucketName") String bucketName, @PathVariable("fileName") String fileName) throws Exception {
        return ResponseData.builder()
                .data(minioService.getFileAsBase64(bucketName, fileName))
                .build();
    }
}
