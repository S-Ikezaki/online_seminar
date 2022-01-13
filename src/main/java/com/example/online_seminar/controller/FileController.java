package com.example.online_seminar.controller;

import com.example.online_seminar.entity.group.File;
import com.example.online_seminar.entity.group.UploadForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Controller
@RequestMapping("files")
public class FileController {

    //        private final FileRepository fileRepository;
    @PostMapping("/filesShare/{groupId}")
    public String fileShare(Model model,
                            @PathVariable("groupId") String groupId) {
        model.addAttribute("groupId", groupId);
        model.addAttribute("uploadForm", new UploadForm());
        return "file_share_menu";
    }

    @PostMapping("/upload")
    String upload(UploadForm uploadForm) {
        MultipartFile multipartFile = uploadForm.getMultipartFile();

        uploadAction(multipartFile);

        return "file_share_menu";
    }

    private void uploadAction(MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();

        Path filePath = Paths.get("C:/UploadTest/" + fileName);

        try{
            byte[] bytes = multipartFile.getBytes();

            OutputStream stream = Files.newOutputStream(filePath);

            stream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



