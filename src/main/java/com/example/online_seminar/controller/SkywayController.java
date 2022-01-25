package com.example.online_seminar.controller;

import com.example.online_seminar.entity.group.GroupMessage;
import com.example.online_seminar.entity.user.User;
import com.example.online_seminar.repository.GroupMessageRepository;
import com.example.online_seminar.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@RestController
@RequestMapping("meetings")
public class SkywayController {

    private final GroupMessageRepository groupMessageRepository;
    private final UserRepository userRepository;

    public SkywayController(
            GroupMessageRepository groupMessageRepository,
            UserRepository userRepository
    ){
        this.groupMessageRepository = groupMessageRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/ajaxTest")
    public String ajaxTest(@RequestBody String note) {
        System.out.println("ajaxTest:きたよ");
        return note;
    }

    @PostMapping("/test")
    public void test(@RequestBody() String data, Model model, Authentication loginUser) {

        String peerId = data.substring(data.indexOf("=") + 1, data.indexOf("&"));
        int groupId = Integer.parseInt(data.substring(data.lastIndexOf("=") + 1));

        System.out.println("成功！！");
        System.out.println("peer_id:" + peerId);
        System.out.println("group_id:" + groupId);

        User loginUserName = userRepository.findByUserId(loginUser.getName());

        GroupMessage groupMessage = new GroupMessage();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sdfCalender = sdf.format((calendar.getTime()));
        int groupMessageId = 0;

        groupMessage.setGroupMessageId(groupMessageId);
        groupMessage.setUserId(loginUser.getName());
        groupMessage.setUserName(loginUserName.getUserName());
        groupMessage.setCreateDatetime(Date.valueOf(sdfCalender));
        groupMessage.setMessageContents("会議を開始しました。会議ID：" + peerId);
        groupMessage.setGroupId(groupId);

        groupMessageRepository.save(groupMessage);

    }
}
