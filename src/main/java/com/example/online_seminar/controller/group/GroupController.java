package com.example.online_seminar.controller.group;

import com.example.online_seminar.entity.group.Group;
import com.example.online_seminar.entity.group.GroupMember;
import com.example.online_seminar.entity.group.GroupMessage;
import com.example.online_seminar.entity.user.User;
import com.example.online_seminar.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("groups")
public class GroupController {

    private final GroupRepository groupRepository;

    private final TagGroupRepository tagGroupRepository;

    private final GroupMessageRepository groupMessageRepository;

    private final GroupMemberRepository groupMemberRepository;

    private final TagRepository tagRepository;

    private final UserRepository userRepository;

    @Autowired
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

    //    検索画面に遷移
    @GetMapping("/search_group")
    public String SearchGroup(@RequestParam("username") String username,
                              @RequestParam("role") String role,
                              Model model) {

        System.out.println("グループサーチ");
        System.out.println(username);
        System.out.println(role);

        List<Group> groupList = new ArrayList<Group>();
        model.addAttribute("groups", groupList);

//        List<Group> groupList = groupRepository.findAll();
//        List<Group> seminar = new ArrayList<Group>();
//        List<Group> competition = new ArrayList<Group>();
//
//        for (Group group: groupList){
//            if(group.getGroupRole() == 0) {
//                seminar.add(group);
//            } else {
//                competition.add(group);
//            }
//        }
//        model.addAttribute("seminars",seminar);
//        model.addAttribute("competitions",competition);

        return "search/search";
    }
    //グループの一件追加用メソッド
    @GetMapping("/addGroup")
    public String addGroup(@Validated @ModelAttribute Group group,
                           Model model, BindingResult result){
        group.setGroupId(group.getGroupId());
        group.setGroupName(group.getGroupName());
        group.setGroupRole(group.getGroupRole());
        group.setGroupMembers(group.getGroupMembers());

        model.addAttribute("groups",group);
        if(result.hasErrors()){
            return "list";
        }
        groupRepository.save(group);
        return "hoge";
    }

    //グループの一覧表示 　データはとってこれる　
    @GetMapping("/showGroupList")
//    @ResponseBody
    public String showGroupList(Model model,String id){
        model.addAttribute("groups",groupRepository.findByUser(id));
        return "group/showGroupList";
    }

    //一件取得用メソッド
    @GetMapping("/{id:[0-9]+")
    public String showGroup(Model model,@PathVariable("id") Long groupId,HttpSession session){
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
    @GetMapping("/showGroupMemberList")
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
    public String showGroupTag(Model model,String groupId) {
        model.addAttribute("hoge", tagRepository.findByGroup(groupId));
        return "hoge";
    }

    //投稿一覧取得
    @GetMapping("/showGroupMessage/{groupId}")
    public String showGroupMessage(Model model,@PathVariable("groupId") String groupId, Authentication loginUser){
        List<GroupMessage> groupMessagesList = groupMessageRepository.findByGroup(groupId);
//        for (GroupMessage groupMessage : groupMessagesList) {
//            groupMessage.getCreateDatetime();
//        }
        model.addAttribute("groupMessages",groupMessagesList);

        /*model.addAttribute("",groupRepository.)*/ //今やってる会議を表示
        return "seminar/seminar_menu";
    }

    @PostMapping("/insertGroupMessage")
    @ResponseBody
    public String insertGroupMessage(@PathVariable Model model,
                                     String groupId,
                                     GroupMessage groupMessage,
                                     Authentication loginUser,
                                     BindingResult result){

        User loginUserName = userRepository.findByUserId(loginUser.getName());
        model.addAttribute("groupMessages", groupRepository.insertGroupMessage(
                groupMessage.getGroupMessageId(),
                loginUser.getName(),
                loginUserName.getUserName(),
                groupMessage.getMessageContents(),
                groupMessage.getGroupId()
                ));

        if(result.hasErrors()){
            return  "list";
        }

        /*groupMessageRepository.save(groupMessage);*/ //必ず必要になると思います

        return "seminar_menu";
    }

    //

    //投稿削除
    @PostMapping("/deleteGroupMessage")
    public String deleteGroupMessage(@PathVariable Long groupMessageId) {
        groupRepository.deleteById(groupMessageId);
        return "hoge";
    }

    //教師による権限付与（情報更新）
    @PostMapping("/updateStudentRole")
    public String updateStudentRole(@RequestBody int groupRole,Model model){
        GroupMember groupMember = new GroupMember();
        groupMember.setGroupRole(groupRole);
        groupMemberRepository.save(groupMember);
        return "hoge";
    }
}
