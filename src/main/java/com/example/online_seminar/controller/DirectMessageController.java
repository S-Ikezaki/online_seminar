package com.example.online_seminar.controller;

import com.example.online_seminar.entity.user.DirectMessage;
import com.example.online_seminar.entity.user.User;
import com.example.online_seminar.repository.DirectMessageRepository;
import com.example.online_seminar.repository.UserRepository;
import com.example.online_seminar.service.DMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/dm")
public class DirectMessageController {

    public final DirectMessageRepository directMessageRepository;
    public final UserRepository userRepository;

    @Autowired
    DMService dmService;

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

        List<User> users = new ArrayList<User>();
        List<DirectMessage> directMessageList = new ArrayList<DirectMessage>();
        directMessageList = directMessageRepository.findAllByAddressUserIdOrCreateUserId(userId, userId);
        Collections.reverse(directMessageList);
        for(DirectMessage directMessage: directMessageList){
            if(directMessage.getCreateUserId().equals(userId)){
                if(!users.contains(directMessage.getAddressUser())){
                    users.add(directMessage.getAddressUser());
                }
            } else {
                if(!users.contains(directMessage.getCreateUser())){
                    users.add(directMessage.getCreateUser());
                }
            }
        }

        return users;
    }

    // DM画面への遷移
    @GetMapping("/")
    public String getMessageUser(Authentication loginUser, Model model) {
        String userId = loginUser.getName();
        List<User> userList = getUserList(userId);

        model.addAttribute("users",userList);

        return "dm/direct_message";
    }

    @PostMapping("/show")
    public String getMessageUserList(Authentication loginUser, Model model) {

        String userId = loginUser.getName();
        List<User> userList = getUserList(userId);

        model.addAttribute("users",userList);

        return "dm/direct_message :: userList";
    }

    // 指定された相手とのDMを取得
    @PostMapping("/message")
    public String showDirectMessagePerson(@RequestBody String addressUserId, Authentication loginUser, Model model) {

        String userId = loginUser.getName();

        String decodeAddressUserId = URLDecoder.decode(addressUserId);
        decodeAddressUserId = decodeAddressUserId.substring(decodeAddressUserId.indexOf("=") + 1);

        User addressUser = new User();

        List<DirectMessage> directMessageList = directMessageRepository.findDirectMessage(decodeAddressUserId,userId);
        addressUser = userRepository.findByUserId(decodeAddressUserId);

        model.addAttribute("dms",directMessageList);
        model.addAttribute("addressUser", addressUser);

        return "dm/direct_message :: messages";
    }

    // ユーザをキーワードで検索（名前）
    @PostMapping("/search")
    public String searchUser(@RequestBody String data, Authentication loginUser , Model model) {

        String decodeKeyword = URLDecoder.decode(data);
        String keyword = decodeKeyword.substring(decodeKeyword.indexOf("=") + 1);

        List<User> searchUserList = new ArrayList<User>();
        searchUserList = userRepository.findAllByUserNameLike("%" + keyword.substring(keyword.indexOf("=") + 1) + "%");

        searchUserList.removeIf(user -> user.getUserId().equals(loginUser.getName()));

        model.addAttribute("searchUsers",searchUserList);

        return "dm/direct_message :: searchList";
    }

    // ダイレクトメッセージの送信
    @PostMapping("/send")
    public String sendMessage(@RequestBody String data, Authentication loginUser, Model model) {

        String decodeData = URLDecoder.decode(data);
        String message = decodeData.substring(decodeData.indexOf("=") + 1, decodeData.indexOf("&"));
        String addressUserId = decodeData.substring(decodeData.lastIndexOf("=") + 1);
        User addressUser = userRepository.findByUserId(addressUserId);
        String addressUserName = addressUser.getUserName();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sdfCalender = sdf.format((calendar.getTime()));

        String loginUserId = loginUser.getName();

        DirectMessage directMessage = new DirectMessage();
        directMessage.setDirectMessageId(0);
        directMessage.setCreateUserId(loginUserId);
        directMessage.setCreateUserName(userRepository.findByUserId(loginUserId).getUserName());
        directMessage.setAddressUserId(addressUserId);
        directMessage.setAddressUserName(addressUserName);
        directMessage.setDirectMessageContent(message);
        directMessage.setCreateDatetime(Date.valueOf(sdfCalender));

        try {
            dmService.saveAndFlushDM(directMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<DirectMessage> directMessageList = directMessageRepository.findDirectMessage(addressUserId,loginUserId);

        List<User> userList = getUserList(loginUserId);

        model.addAttribute("users",userList);
        model.addAttribute("addressUser", addressUser);
        model.addAttribute("dms", directMessageList);

        return "dm/direct_message :: messages";
    }

}

