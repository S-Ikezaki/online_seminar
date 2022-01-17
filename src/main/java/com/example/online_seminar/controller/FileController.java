package com.example.online_seminar.controller;

import com.example.online_seminar.entity.group.File;
import com.example.online_seminar.entity.group.UploadForm;
import com.example.online_seminar.entity.user.User;
import com.example.online_seminar.repository.FileRepository;
import com.example.online_seminar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;


@Controller
@RequestMapping("files")
public class FileController{
    private final UserRepository userRepository;
    private final FileRepository fileRepository;
        @Autowired
    public FileController(UserRepository userRepository,
                          FileRepository fileRepository){
    this.userRepository = userRepository;
    this.fileRepository = fileRepository;
        }



    //        private final FileRepository fileRepository;
    @PostMapping("/filesShare/{groupId}")
    public String fileShare(Model model,
                            @PathVariable("groupId") String groupId) {
        model.addAttribute("groupId", groupId);
        model.addAttribute("uploadForm", new UploadForm());
        return "file_share_menu";
    }

//    @PostMapping("/upload")
//    private String  upload(UploadForm uploadForm,String groupId) {
//        MultipartFile multipartFile = uploadForm.getMultipartFile();
//
//        uploadAction(multipartFile);
//
//        return "file_share_menu";
//    }

    @PostMapping("/upload")
    private String upload(UploadForm uploadForm,
                          Model model,
                          int groupId,
                          Authentication loginUser,
                          BindingResult result) {
            MultipartFile multipartFile = uploadForm.getMultipartFile();
            File file = new File();
            String fileName = multipartFile.getOriginalFilename();

        Path filePath = Paths.get("C:/UploadTest/" + fileName);
//        Path filePath = Paths.get("グループの共有ファイルフォルダ/" + fileName);

        try{
            byte[] bytes = multipartFile.getBytes();

            OutputStream stream = Files.newOutputStream(filePath);

            stream.write(bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }

        User loginUserName = userRepository.findByUserId(loginUser.getName());

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sdfCalender = sdf.format((calendar.getTime()));
        String fileId = "0";

        file.setFileId(fileId);
        file.setFileName(fileName);
        file.setFilePath(filePath.toString());
        file.setUserName(loginUserName.getUserName());
        file.setCreateDatetime(Date.valueOf(sdfCalender));
        file.setGroupId(groupId);

        if(result.hasErrors()){
            return  "error";
        }

        fileRepository.save(file);

        model.addAttribute("groupId",groupId);
        return "file_share_menu";
    }
}



