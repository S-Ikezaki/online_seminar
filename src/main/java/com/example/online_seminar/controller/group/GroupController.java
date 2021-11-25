package com.example.online_seminar.controller.group;

import com.example.online_seminar.entity.group.Group;
import com.example.online_seminar.repository.GroupMemberRepository;
import com.example.online_seminar.repository.GroupRepository;
import com.example.online_seminar.repository.TagGroupRepository;
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

    @Autowired
    private TagGroupRepository tagGroupRepository;

    @Autowired
    private GroupMemberRepository groupMemberRepository;

    @GetMapping("/add")
    public String addGroup(@ModelAttribute Group group){
        return " ";
    }

    //グループの一件追加用メソッド
    @GetMapping("/insert")
    public String insertOne(@RequestBody Group group,Model model){
        model.addAttribute("",groupRepository.findById(group.getGroupId()));
        return " ";
    }

    //グループの一覧表示
    @GetMapping("/group/showGroupList")
    @ResponseBody
    public String showGroupList(Model model, HttpSession session){
        model.addAttribute("",groupRepository.findAll());
        return "";
    }

    //一件取得用メソッド
    @GetMapping("/group/{id:[0-9]+")
    public String showGroup(Model model,@PathVariable("id") Long groupId,HttpSession session){
        //findById実行
        model.addAttribute("",groupRepository.findById(groupId));
        return "";
    }

    //一件削除
    @PostMapping("/deleteOne/{id:.+")
    public String deleteOne(@PathVariable Long groupId){
        groupRepository.deleteById(groupId);
        return "一件削除";
    }

    //グループのメンバー一覧表示（？）
    @GetMapping("/group/showGroupMemberList")
    @ResponseBody
    public String showGroupMemberList(Model model, HttpSession session){
        session.setAttribute("",groupMemberRepository.findAll());
        model.addAttribute("",groupRepository.findAll());
        return "";
    }

}
