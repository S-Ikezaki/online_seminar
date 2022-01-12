package com.example.online_seminar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("files")
public class FileController {

//        private final FileRepository fileRepository;
        @PostMapping("/filesShare/{groupId}")
        public String fileShare(Model model,
                                @PathVariable("groupId") String groupId) {
            model.addAttribute("groupId",groupId);
            return "file_share_menu";
        }


    }

