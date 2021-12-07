package com.example.online_seminar.controller;

import com.example.online_seminar.repository.DirectMessageRepository;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dm")
public class DirectMessageController {

    public final DirectMessageRepository directMessageRepository;

    public DirectMessageController(
            DirectMessageRepository directMessageRepository;
    ) {
        this.directMessageRepository = directMessageRepository;
    }

    // メッセージの履歴があるユーザ一覧取得
    public String getMessageUser(@ModelAttribute User id, Model model, BindingResult result) {
        directMessageRepository.findAllById(id);
    }

    // 送信先、送信先とのメッセージを取得
    public String getAddress(@ModelAttribute User id, Model model, BindingResult result) {



        if (result.hasErrors()) {
            return "";
        }

        return "";
    }


}
