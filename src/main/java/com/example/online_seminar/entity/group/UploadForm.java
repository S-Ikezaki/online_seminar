package com.example.online_seminar.entity.group;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UploadForm {
    private MultipartFile multipartFile;
}
