package com.example.online_seminar.controller.group;

import com.example.online_seminar.repository.GroupMemberRepository;
import com.example.online_seminar.repository.GroupRepository;
import com.example.online_seminar.repository.TagGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//使用するかわからない
@RestController
@RequestMapping("groupMembers")
public class GroupMemberController {

    private final GroupRepository groupRepository;

    private final GroupMemberRepository groupMemberRepository;

    public GroupMemberController(GroupRepository groupRepository,
                                 GroupMemberRepository groupMemberRepository) {
        this.groupRepository = groupRepository;
        this.groupMemberRepository = groupMemberRepository;
    }
}
