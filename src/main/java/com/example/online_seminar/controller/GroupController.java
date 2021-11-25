package com.example.online_seminar.controller;

import com.example.online_seminar.entity.group.Group;
import com.example.online_seminar.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

//HTTPを受け取るクラス
@RestController
@RequestMapping("groups")
public class GroupController {

    @Autowired
    private GroupRepository groupRepository;

    //一件追加用メソッド
    @PostMapping("/insert")
    public String insertOne(@RequestBody Group group){
        groupRepository.save(group);
        return "一件追加";
    }

    @GetMapping("/group/showGroupList")
    @ResponseBody
    public List<Group> showGroupList(Model model, HttpSession session){
        //全件取得
        session.setAttribute("");
       return groupRepository.findAll();
    }

    //一件取得用メソッド
    @GetMapping("/group/{id:[0-9]+")
    public Optional<Group> showGroup(@PathVariable("id") int groupId){
        //findById実行
        return groupRepository.findById(groupId);
    }

    //一件更新メソッド
    @PostMapping("/group/updateOne")
    public String updateOne(@RequestBody Group group) {
        groupRepository.save(group);
        return "一件更新";
    }

    //一件削除
    @PostMapping("/deleteOne/{id:.+")
    public String deleteOne(@PathVariable("id") int groupId){
        groupRepository.deleteById(groupId);
        return "一件削除";
    }
}
