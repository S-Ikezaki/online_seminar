package com.example.online_seminar.service;

import com.example.online_seminar.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.online_seminar.model.group.Group;
import com.example.online_seminar.model.group.GroupMember;
import com.example.online_seminar.model.group.GroupMessage;
//meetingもつかうかも

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GroupService {

    final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<Group> search(String group_Id, String group_name,int role,String group_bio,boolean delete_flag){
        List<Group> result;
        result = groupRepository.findAll();
        return result;
    }
}
