package com.example.online_seminar.controller;

import com.example.online_seminar.entity.group.Meeting;
import com.example.online_seminar.entity.group.MeetingMember;
import com.example.online_seminar.entity.user.User;
import com.example.online_seminar.repository.GroupMessageRepository;
import com.example.online_seminar.repository.MeetingMemberRepository;
import com.example.online_seminar.repository.MeetingRepository;
import com.example.online_seminar.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        System.out.println(data);

        String peerId = data.substring(data.indexOf("=") + 1, data.indexOf("&"));
        int groupId = Integer.parseInt(data.substring(data.lastIndexOf("=") + 1));

        User loginUserName = userRepository.findByUserId(loginUser.getName());

        Meeting meeting = new Meeting();
        meeting.setGroupId(groupId);
        meeting.setUserName(loginUserName.getUserName());
        meeting.setPeerId(peerId);

        meetingRepository.save(meeting);

        MeetingMember meetingMember = new MeetingMember();
        meetingMember.setGroupId(groupId);
        meetingMember.setUserName(loginUserName.getUserName());

        meetingMemberRepository.save(meetingMember);

    }

    @PostMapping("/join")
    public void joinMeeting(@RequestBody() String data, Model model, Authentication loginUser){

        System.out.println(data);

        int groupId = Integer.parseInt(data.substring(data.lastIndexOf("=") + 1));

        User loginUserName = userRepository.findByUserId(loginUser.getName());

        MeetingMember meetingMember = new MeetingMember();
        meetingMember.setGroupId(groupId);
        meetingMember.setUserName(loginUserName.getUserName());

        meetingMemberRepository.save(meetingMember);

    }

    @PostMapping("/close")
    public void closeMeeting(@RequestBody String data, Authentication loginUser) {

        System.out.println("削除前！！");

        int groupId = Integer.parseInt(data.substring(data.lastIndexOf("=") + 1));
        User loginUserName = userRepository.findByUserId(loginUser.getName());

        System.out.println(loginUserName.getUserName());
        meetingMemberRepository.deleteByUserName(loginUserName.getUserName());
        if(meetingMemberRepository.countByGroupId(groupId) == 0) {
            meetingRepository.deleteByGroupId(groupId);
        }

        System.out.println("削除した!!");
//        meetingMemberRepository.deleteAllByGroupId(groupId);


//        return "";
    }

}
