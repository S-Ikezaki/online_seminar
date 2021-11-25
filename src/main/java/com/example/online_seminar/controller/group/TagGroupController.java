package com.example.online_seminar.controller.group;

import com.example.online_seminar.repository.GroupRepository;
import com.example.online_seminar.repository.TagGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;

//使うかわからない
public class TagGroupController {

    @Autowired
    GroupRepository groupRepository;
    @Autowired
    TagGroupRepository tagGroupRepository;
}
