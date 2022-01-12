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
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

    // 検索画面に遷移
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

    //検索ボタンが押された時の処理
    @PostMapping("/search_group_detail")
    public String SearchGroupDetail(@RequestParam(value = "keyword", required = false) String keyword,
                                    @RequestParam(value = "checkBoxSem", required = false) String checkBoxSem,
                                    @RequestParam(value = "checkBoxCompPre", required = false) String checkBoxCompP,
                                    @RequestParam(value = "checkBoxCompSub", required = false) String checkBoxCompS,
                                    Model model){
//      値確認用
        System.out.println(keyword);
        System.out.println(checkBoxSem);
        System.out.println(checkBoxCompP);
        System.out.println(checkBoxCompS);

        int roleA =  0;
        int roleB = 1;
        int roleC = 2;

        //ロール分け(まだ途中(現状発表型か提出型の片方のコンペしか表示できない))
        if (checkBoxCompP == null && checkBoxCompS == null) {
            roleA = 0;
            roleB = 0;
            roleC = 0;
        }else if (checkBoxSem == null && checkBoxCompS == null) {
            roleA = 1;
            roleB = 1;
            roleC = 1;
        } else if (checkBoxSem == null && checkBoxCompP == null) {
            roleA = 2;
            roleB = 2;
            roleC = 2;
        } else if (checkBoxCompS == null){
            roleA = 0;
            roleB = 1;
            roleC = 1;
        } else if (checkBoxCompP == null) {
            roleA = 0;
            roleB = 2;
            roleC = 2;
        } else if (checkBoxSem == null) {
            roleA = 1;
            roleB = 1;
            roleC = 2;
        } else {
            roleA = 0;
            roleB = 1;
            roleC = 2;
        }

        if (checkBoxSem == null && checkBoxCompP == null && checkBoxCompS == null && keyword == null) {
            return "search/search";
        }

        List<Group> groupList = groupRepository.findByRoleNq(keyword, roleA, roleB, roleC);

        model.addAttribute("groupList", groupList);

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

    //グループを一件削除
    @PostMapping("/deleteOne/{id:.+")
    public String deleteGroupOne(@PathVariable String groupId){
        groupRepository.deleteById(Long.valueOf(groupId));
        return "一件削除";
    }

    //グループのメンバー一覧表示
    @GetMapping("/showGroupMemberList/{groupId}")
    @ResponseBody
    public String showGroupMemberList(Model model,@PathVariable("groupId") String groupId){
        

        return "hoge";
    }

    //グループのタグを表示
    @GetMapping("/showGroupTag")
    public String showGroupTag(Model model,String groupId) {
        model.addAttribute("hoge", tagRepository.findByGroup(groupId));
        return "hoge";
    }

    //投稿一覧取得
    //グループ、ゼミのメインメニューを表示するためのメソッド
    @GetMapping("/showGroupMessage/{groupId}")
    public String showGroupMessage(Model model,@PathVariable("groupId") String groupId, Authentication loginUser){
        List<GroupMessage> groupMessagesList = groupMessageRepository.findByGroup(groupId);
//        for (GroupMessage groupMessage : groupMessagesList) {
//            groupMessage.getCreateDatetime();
//        }
        List<Group> group = groupRepository.findById(groupId);
        model.addAttribute("groupMessages",groupMessagesList);
        System.out.println(groupId);
        model.addAttribute("groupId",groupId);
        model.addAttribute("username",loginUser.getName());


//        System.out.println(group.get(0).getGroupName());

        /*model.addAttribute("",groupRepository.)*/ //今やってる会議を表示
        if (group.get(0).getGroupRole() == 0) {
            return "seminar/seminar_menu";
        }else if(group.get(0).getGroupRole() == 1){
            model.addAttribute("groups",group);
            return "seminar/seminar_competition_presentation";
        }else {
            model.addAttribute("groups",group);
            return "seminar/seminar_competition_submission";
        }
    }

    //グループのメッセージ追加
    @GetMapping("/addGroupMessage")
    public String addGroupMessage(Model model,
                                  String groupId,
                                  GroupMessage groupMessage,
                                  Authentication loginUser,
                                  BindingResult result){

        User loginUserName = userRepository.findByUserId(loginUser.getName());

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sdfCalender = sdf.format((calendar.getTime()));
        int groupMessageId = 0;

        groupMessage.setGroupMessageId(groupMessageId);
        groupMessage.setUserId(loginUser.getName());
        groupMessage.setUserName(loginUserName.getUserName());
        groupMessage.setCreateDatetime(Date.valueOf(sdfCalender));
        groupMessage.setMessageContents(groupMessage.getMessageContents());
        groupMessage.setGroupId(groupId);

        if(result.hasErrors()){
            return  "error";
        }

        groupMessageRepository.save(groupMessage);
        groupId = groupMessage.getGroupId();

        return "redirect:showGroupMessage/"+groupId;
    }


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
