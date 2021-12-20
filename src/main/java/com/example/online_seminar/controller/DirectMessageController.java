package com.example.online_seminar.controller;

import com.example.online_seminar.entity.user.DirectMessage;
import com.example.online_seminar.entity.user.User;
import com.example.online_seminar.repository.DirectMessageRepository;
import com.example.online_seminar.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/dm")
public class DirectMessageController {

    public final DirectMessageRepository directMessageRepository;
    public final UserRepository userRepository;

    public DirectMessageController(
            DirectMessageRepository directMessageRepository,
            UserRepository userRepository
    ) {
        this.directMessageRepository = directMessageRepository;
        this.userRepository = userRepository;
    }

    // メッセージの履歴があるユーザ一覧取得
    @GetMapping("/")
    public String getMessageUser(Authentication id, Model model) {
        String userId = id.getName();
        System.out.println(userId);

        List<DirectMessage> directMessages = directMessageRepository.findAll();
        List<DirectMessage> directMessageList = directMessages;
        Collections.reverse(directMessageList);

        List<User> userList = new ArrayList<User>();
        for(DirectMessage directMessage: directMessageList){
            if(directMessage.getCreateUserId().equals(userId)){
                if(!userList.contains(directMessage.getAddressUser())){
                    userList.add(directMessage.getAddressUser());
                }
            } else {
                if(!userList.contains(directMessage.getCreateUser())){
                    userList.add(directMessage.getCreateUser());
                }
            }
        }

        model.addAttribute("dms",directMessages);
        model.addAttribute("users",userList);

        return "dm/direct_message";
    }

    // ダイレクトメッセージの送信
//    public String sendMessage(@ModelAttribute("message") String message, BindingResult result) {
//
//        if (result.hasErrors()){
//            return "login";
//        }
//
//        DirectMessage directMessage = new DirectMessage();
//
//        directMessageRepository.save(message);
//        return "dm/direct_message";
//    }

    // 送信先、送信先とのメッセージを取得
//    public String getAddress(@ModelAttribute User id, Model model, BindingResult result) {
//        if (result.hasErrors()) {
//            return "";
//        }
//
//        return "";
//    }


}
