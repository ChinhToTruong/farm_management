package com.example.zev.controller;

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
    public String upload(@RequestParam("file") MultipartFile file) throws Exception {
        return minioService.uploadFile(file);
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
}
