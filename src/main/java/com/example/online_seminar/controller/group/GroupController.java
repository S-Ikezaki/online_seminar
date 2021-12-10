package com.example.online_seminar.controller.group;

import com.example.online_seminar.entity.group.Group;
import com.example.online_seminar.entity.group.GroupMessage;
import com.example.online_seminar.entity.user.User;
import com.example.online_seminar.repository.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("groups")
public class GroupController {

    private final GroupRepository groupRepository;

    private final TagGroupRepository tagGroupRepository;

    private final GroupMessageRepository groupMessageRepository;

    private final GroupMemberRepository groupMemberRepository;

    private final TagRepository tagRepository;

    private final UserRepository userRepository;

    public GroupController(GroupRepository groupRepository,
                           TagGroupRepository tagGroupRepository,
                           GroupMessageRepository groupMessageRepository,
                           GroupMemberRepository groupMemberRepository,
                           TagRepository tagRepository,
                           UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.tagGroupRepository = tagGroupRepository;
        this.groupMessageRepository = groupMessageRepository;
        this.groupMemberRepository = groupMemberRepository;
        this.tagRepository = tagRepository;
        this.userRepository = userRepository;
    }

    /*@GetMapping("/add")
    public String addGroup(@ModelAttribute Group group){
        return " ";
    }*/

    //グループの一件追加用メソッド
    @GetMapping("/addGroup")
    public String addGroup(@Validated @ModelAttribute Group group,
                           Model model, BindingResult result){
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
    public String deleteGroupOne(@PathVariable String groupId){
        groupRepository.deleteById(Long.valueOf(groupId));
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

    //グループのタグを表示
    @GetMapping("/showGroupTag")
    public String showGroupTag(Model model,Group group) {
        model.addAttribute("hoge", tagRepository.findByGroup(group));
        return "hoge";
    }

    //投稿一覧取得
    @GetMapping("/showGroupMessage")
    @ResponseBody
    public String showGroupMessage(@PathVariable Model model, String groupId){
        model.addAttribute("hoge", groupRepository.findMessageByGroup(groupId));
        return "hoge";
    }
    //投稿削除
    @PostMapping("/deleteGroupMessage")
    public String deleteGroupMessage(@PathVariable Long groupMessageId) {
        groupRepository.deleteById(groupMessageId);
        return "hoge";
    }

    //教師による権限付与（情報更新）
    @PostMapping("/updateStudentRole")
    public String updateStudentRole(@RequestBody User user){
        userRepository.save(user);
        return "hoge";
    }







}
