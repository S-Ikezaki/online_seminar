package com.example.online_seminar.controller;

import com.example.online_seminar.model.Group;
import com.example.online_seminar.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class GroupController {
    private final GroupRepository repository;

    public GroupController(
            @Autowired
                    GroupRepository repository) {
        this.repository = repository;
    }

    //入力された値(現在仮で直入力中)でgroup_mstの値を取り出しlist変数でリスト化しindexに渡す
    @PostMapping("/group")
    private String index(Model model) {
        List<Group> list = repository.getAll();
        model.addAttribute("GroupList", list);
        return "index";
    }

    //group_id検索用のformに遷移
    @GetMapping("/form")
    private String input(@ModelAttribute Group group, Model model) {
        model.addAttribute("group", group);

        return "input";
    }
}

    //検索されたgroup_idを表示(後でgroupテーブルのwhere検索用のものに変える)
//    @PostMapping("/form")
//    private String confirm(@ModelAttribute Group group, Model model) {
//        model.addAttribute("group", group);
//
//        return "confirm";
//    }
