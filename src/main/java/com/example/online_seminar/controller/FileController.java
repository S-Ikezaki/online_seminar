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

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


@Controller
@RequestMapping("files")
public class FileController {
    private final UserRepository userRepository;
    private final FileRepository fileRepository;

    @Autowired
    public FileController(UserRepository userRepository,
                          FileRepository fileRepository) {
        this.userRepository = userRepository;
        this.fileRepository = fileRepository;
    }


    //        private final FileRepository fileRepository;
    @PostMapping("/filesShare")
    public String filesShare(Model model, String groupId) {
        model.addAttribute("groupId", groupId);
        //↓いらんかも
        model.addAttribute("uploadForm", new UploadForm());
        System.out.println("きたよ");
        List<File> list = fileRepository.findAll();
        model.addAttribute("fileList", list);

        return "file_share_menu";
    }

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

        try {
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
        int fileId = 0;

//        file.setFileId(fileId);
        file.setFileName(fileName);
        file.setFilePath(filePath.toString());
        file.setUserName(loginUserName.getUserName());
        file.setCreateDatetime(Date.valueOf(sdfCalender));
        file.setGroupId(groupId);

        if (result.hasErrors()) {
            return "error";
        }

        fileRepository.save(file);

        model.addAttribute("groupId", groupId);
        return "forward:/files/filesShare";
    }

    @PostMapping("/download")
    public String download(int fileId,
                           HttpServletResponse response,
                           Model model
    ) {

        File file = fileRepository.findByFileId(fileId);

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=\"" + file.getFileName() + "\"");
        response.setHeader("Cache-Control", "private");
        response.setHeader("Pragma", "");

        Path filePath = Paths.get(file.getFilePath());
        OutputStream out = null;
        InputStream in = null;
        try {
            out = response.getOutputStream();
            in = Files.newInputStream(filePath);
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = in.read(bytes, 0, bytes.length)) != -1){
                out.write(bytes, 0, len);
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "forward:/files/filesShare";
    }

    @PostMapping("/delete")
    public String delete(int fileId,Model model) {
        File file = fileRepository.findByFileId(fileId);
        Path filePath = Paths.get(file.getFilePath());

        try{
            Files.delete(filePath);
        }catch (IOException e){
            e.printStackTrace();
        }

        fileRepository.deleteByFileId(fileId);

        List<File> list = fileRepository.findAll();
//        model.addAttribute("fileList", list);
//        model.addAttribute("uploadForm", new UploadForm());
//        return "file_share_menu";
        return "forward:/files/filesShare";
    }
}



