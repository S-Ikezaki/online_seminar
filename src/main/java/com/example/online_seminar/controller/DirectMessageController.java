package com.example.online_seminar.controller;

import com.example.online_seminar.entity.user.DirectMessage;
import com.example.online_seminar.entity.user.User;
import com.example.online_seminar.repository.DirectMessageRepository;
import com.example.online_seminar.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    // DMのやりとりのあるユーザを取得するメソッド
    @ResponseBody
    public List<User> getUserList(String userId) {

        List<DirectMessage> directMessageList = directMessageRepository.findAllByAddressUserIdOrCreateUserId(userId,userId);
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
        return userList;
    }

    // メッセージの履歴があるユーザ一覧取得
    @GetMapping("/")
    public String getMessageUser(Authentication id, Model model) {
        String userId = id.getName();
        System.out.println(userId);

        List<User> userList = getUserList(userId);

        model.addAttribute("users",userList);

        return "dm/direct_message";
    }

    // 指定された相手とのDMを取得
    @PostMapping("dmToPerson/{addressUserId}")
    public String showDirectMessagePerson(@PathVariable("addressUserId") String addressUserId,Authentication user, Model model) {
        System.out.println("私とのDM楽しい？");
        System.out.println(addressUserId);

        String userId = user.getName();

        List<DirectMessage> directMessages = directMessageRepository.findDirectMessage(addressUserId,userId);
        List<User> userList = getUserList(userId);

        model.addAttribute("users", userList);
        model.addAttribute("dms",directMessages);

        return "dm/direct_message";
    }

    // ユーザをキーワードで検索（名前）
    @GetMapping("/search")
    public String searchUser(@ModelAttribute("keyword") String keyword, BindingResult result,Model model) {

        System.out.println(keyword);
        List<User> userList = userRepository.findAll();
        List<DirectMessage> directMessages = new ArrayList<DirectMessage>();

        System.out.println(userList.get(0).getUserName());
        model.addAttribute("users", userList);
//        model.addAttribute("dms", directMessages);

        return "dm/direct_message";
    }

    // ダイレクトメッセージの送信
//    @PostMapping("/send")
//    public String sendMessage(@ModelAttribute("message") String message, BindingResult result) {
//
//        if (result.hasErrors()){
//            return "login";
//        }
//
//        DirectMessage directMessage = new DirectMessage();
//        directMessage.setDirectMessageId();
//        directMessage.setCreateUserId();
//        directMessage.setCreateUserName();
//        directMessage.setAddressUserId();
//        directMessage.setAddressUserName();
//        directMessage.setDirectMessageContent(message);
//        directMessage.setCreateDatetime();
//
//        directMessageRepository.save(message);
//        return "dm/direct_message";
//    }

}
