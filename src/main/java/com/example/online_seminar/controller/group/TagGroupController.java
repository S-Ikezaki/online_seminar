package com.example.online_seminar.controller.group;

import com.example.online_seminar.repository.GroupRepository;
import com.example.online_seminar.repository.TagGroupRepository;
import com.example.online_seminar.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("tags")
public class TagGroupController {

    final GroupRepository groupRepository;
    final TagGroupRepository tagGroupRepository;
    final TagRepository tagRepository;

    public TagGroupController(GroupRepository groupRepository, TagGroupRepository tagGroupRepository,TagRepository tagRepository) {
        this.groupRepository = groupRepository;
        this.tagGroupRepository = tagGroupRepository;
        this.tagRepository = tagRepository;
    }

    //グループを作成した際に新しいタグを追加、すでにあるタグを持ってくるメソッド
    @PostMapping("/addTag")
    public String addTag(){


        return "hoge";
    }

}
