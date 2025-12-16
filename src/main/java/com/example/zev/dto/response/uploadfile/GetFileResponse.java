package com.example.zev.dto.response.uploadfile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetFileResponse {
    private String fileName;
    private String base64Content;
}
