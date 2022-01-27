package com.example.online_seminar.controller;

import com.example.online_seminar.entity.group.GroupMessage;
import com.example.online_seminar.entity.group.Meeting;
import com.example.online_seminar.entity.group.MeetingMember;
import com.example.online_seminar.entity.user.User;
import com.example.online_seminar.repository.GroupMessageRepository;
import com.example.online_seminar.repository.MeetingMemberRepository;
import com.example.online_seminar.repository.MeetingRepository;
import com.example.online_seminar.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@RestController
@RequestMapping("meetings")
public class SkywayController {

    private final GroupMessageRepository groupMessageRepository;
    private final UserRepository userRepository;
    private final MeetingRepository meetingRepository;
    private final MeetingMemberRepository meetingMemberRepository;

    public SkywayController(
            GroupMessageRepository groupMessageRepository,
            UserRepository userRepository,
            MeetingRepository meetingRepository,
            MeetingMemberRepository meetingMemberRepository
    ){
        this.groupMessageRepository = groupMessageRepository;
        this.userRepository = userRepository;
        this.meetingRepository = meetingRepository;
        this.meetingMemberRepository = meetingMemberRepository;
    }

    @PostMapping("/open")
    public void openMeeting(@RequestBody() String data, Model model, Authentication loginUser) {

        String peerId = data.substring(data.indexOf("=") + 1, data.indexOf("&"));
        int groupId = Integer.parseInt(data.substring(data.lastIndexOf("=") + 1));

        User loginUserName = userRepository.findByUserId(loginUser.getName());

        Meeting meeting = new Meeting();
        meeting.setGroupId(groupId);
        meeting.setUserName(loginUserName.getUserName());

        meetingRepository.save(meeting);


        MeetingMember meetingMember = new MeetingMember();
        meetingMember.setGroupId(groupId);
        meetingMember.setUserName(loginUserName.getUserName());

        meetingMemberRepository.save(meetingMember);

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

    @GetMapping("/close")
    public String closeMeeting(@RequestBody String groupId) {

        

        return "";
    }

}
