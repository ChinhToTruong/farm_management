package com.example.zev.dto.response.uploadfile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UploadFileResponse {
    private String fileName;
    private String fileType;
    private String filePath;
}
