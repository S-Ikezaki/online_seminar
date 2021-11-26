package com.example.online_seminar.controller.group;

import com.example.online_seminar.entity.group.Group;
import com.example.online_seminar.repository.GroupMemberRepository;
import com.example.online_seminar.repository.GroupRepository;
import com.example.online_seminar.repository.TagGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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

    /*@GetMapping("/add")
    public String addGroup(@ModelAttribute Group group){
        return " ";
    }*/

    //グループの一件追加用メソッド
    @GetMapping("/addGroup")
    public String addGroup(@Validated @ModelAttribute Group group, Model model, BindingResult result){
        model.addAttribute("groups",groupRepository.findAll());
        if(result.hasErrors()){
            return "hoge";
        }
        groupRepository.save(group);
        return "hoge";
    }

    //グループの一覧表示
    @GetMapping("/group/showGroupList")
    @ResponseBody
    public String showGroupList(Model model){
        model.addAttribute("hoge",groupRepository.findAll());
        return "hoge";
    }

    //参加しているグループの一覧表示
    @GetMapping("/group/showUserGroupList")
    @ResponseBody
    public String showUserGroupList(Model model,HttpSession session){
        session.setAttribute("groupMembers",groupMemberRepository.findAll());
        model.addAttribute("groups",groupRepository.findAll());
        return "student_main_menu";
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
    public String deleteGroupOne(@PathVariable Long groupId){
        groupRepository.deleteById(groupId);
        return "一件削除";
    }

    //グループのメンバー一覧表示（？）
    @GetMapping("/group/showGroupMemberList")
    @ResponseBody
    public String showGroupMemberList(Model model, HttpSession session){
        //グループリポジトリからすべてを取得、セッションスコープに保存
        session.setAttribute("hoge",groupRepository.findAll());
        //内部結合したグループメンバーの全レコード取得、リクエストスコープに保存
        model.addAttribute("hoge",groupMemberRepository.findAll());
        return "hoge";
    }


}
