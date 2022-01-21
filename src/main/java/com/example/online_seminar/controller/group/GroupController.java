package com.example.online_seminar.controller.group;

import com.example.online_seminar.entity.group.Group;
import com.example.online_seminar.entity.group.GroupMember;
import com.example.online_seminar.entity.group.GroupMessage;
import com.example.online_seminar.entity.user.Participation;
import com.example.online_seminar.entity.user.Request;
import com.example.online_seminar.entity.user.User;
import com.example.online_seminar.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.xml.crypto.Data;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("groups")
public class GroupController {

    private final GroupRepository groupRepository;

    private final TagGroupRepository tagGroupRepository;

    private final GroupMessageRepository groupMessageRepository;

    private final GroupMemberRepository groupMemberRepository;

    private final TagRepository tagRepository;

    private final UserRepository userRepository;

    private final RequestRepository requestRepository;

    private final ParticipationRepository participationRepository;

    @Autowired
    public GroupController(GroupRepository groupRepository,
                           TagGroupRepository tagGroupRepository,
                           GroupMessageRepository groupMessageRepository,
                           GroupMemberRepository groupMemberRepository,
                           TagRepository tagRepository,
                           UserRepository userRepository,
                           RequestRepository requestRepository,
                           ParticipationRepository participationRepository) {
        this.groupRepository = groupRepository;
        this.tagGroupRepository = tagGroupRepository;
        this.groupMessageRepository = groupMessageRepository;
        this.groupMemberRepository = groupMemberRepository;
        this.tagRepository = tagRepository;
        this.userRepository = userRepository;
        this.requestRepository = requestRepository;
        this.participationRepository = participationRepository;
    }

    /*@GetMapping("/add")
    public String addGroup(@ModelAttribute Group group){
        return " ";
    }*/

    //参加申請画面に遷移
    @GetMapping("/apply")
    //searchの参加申請ボタンを押された時groupRoleを受け取りゼミかコンペで遷移先を分ける?
    public String Transition(@RequestParam("groupRole") int role,
                             @RequestParam("groupId") String id,
                             @RequestParam("userId") String userId,
                             Model model
                            ){

        //確認用
        System.out.println(role);
        System.out.println(id);
        System.out.println(userId);

        //ロールによって遷移先を分ける
        if (role == 0) {
            model.addAttribute("id",id);
            model.addAttribute("userId", userId);
            return "seminar/seminar_apply";
        } else {
            model.addAttribute("id",id);
            model.addAttribute("userId", userId);
            return "competition/apply";
        }
    }
    //申請ボタンを押された時の処理
    @GetMapping("/apply/execution")
    public String Execution(@RequestParam("groupId") int id,
                            @RequestParam("userId") String userId,
                            @RequestParam("comment") String comment,
                            Participation participation,
                            BindingResult result){

        System.out.println(id);
        System.out.println("aaa");

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sdfCalender = sdf.format((calendar.getTime()));

        List<GroupMember> groupLeader = groupMemberRepository.findByGroupRoleNq(id);

        List<GroupMember> groupMember = groupMemberRepository.findByUserId(userId);

        //受け取り確認用
        System.out.println(groupLeader);
        System.out.println(id);//学生が申請したグループのID
        System.out.println(userId);//申請した学生のID
        System.out.println(groupMember.get(0).getUserName()); //申請した学生の名前
        System.out.println(groupLeader.get(0).getUserId()); //申請を受け取る教師のID
        System.out.println(comment);//参加申請画面で書かれたコメント

        participation.setCreateUserId(userId);
        participation.setCreateUserName(groupMember.get(0).getUserName());
        participation.setGroupId(id);
        participation.setAddressUserId(groupLeader.get(0).getUserId());
        participation.setParticipationContents(comment);
        participation.setCreateDatetime(Date.valueOf(sdfCalender));

        if(result.hasErrors()){
            return  "error";
        }

        System.out.println(participation);

        participationRepository.save(participation);

        return "main_menu";
    }

    //ゼミ作成リクエスト画面に遷移
    @GetMapping("/request_seminar")
    public String RequestSeminar(@RequestParam("userId") String userId,
                                 Model model){

        //確認用
        System.out.println("動作確認");
        System.out.println(userId);

        model.addAttribute("requestUser", userId);

        return "student/request_send";
    }

    //リクエスト提示ボタンが押された時の処理
    @PostMapping("/request_presentation")
    public String RequestPresentation(Model model){

        return "main_menu";
    }

    // 検索画面に遷移
    @GetMapping("/search_group")
    public String SearchGroup(@RequestParam("userId") String userId,
                              @RequestParam("role") String role,
                              Model model) {

        //確認用
        System.out.println("グループサーチ");
        System.out.println(userId);
        System.out.println(role);

        List<Group> groupList = new ArrayList<Group>();

        model.addAttribute("groups", groupList);
        model.addAttribute("userId", userId);

        return "search/search";
    }

    //検索ボタンが押された時の処理
    @PostMapping("/search_group_detail")
    public String SearchGroupDetail(@RequestParam(value = "keyword", required = false) String keyword,
                                    @RequestParam(value = "seminar1", required = false) String checkBoxSem,
                                    @RequestParam(value = "seminar2", required = false) String checkBoxCompP,
                                    @RequestParam(value = "seminar3", required = false) String checkBoxCompS,
                                    @RequestParam(value = "checkBoxReq", required = false) String checkBoxReq,
                                    @RequestParam("userId") String userId,
                                    Model model){
//      値確認用
        System.out.println(keyword);
        System.out.println(checkBoxSem);
        System.out.println(checkBoxCompP);
        System.out.println(checkBoxCompS);
        System.out.println(checkBoxReq);
        System.out.println(userId);

        if (Objects.equals(checkBoxReq, "request")) {
            System.out.println("仮成功");
            List<Request> requestList = requestRepository.findAllSelect();
            model.addAttribute("requestList",requestList);

            return "search/search";
        }

        if (checkBoxSem == null && checkBoxCompP == null && checkBoxCompS == null && keyword == null) {
            model.addAttribute("userId", userId);
            return "search/search";
        }

        int roleA =  0;
        int roleB = 1;
        int roleC = 2;

        //ロール分け
        //ゼミのみ
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

        List<Group> groupList = groupRepository.findByRoleNq(keyword, roleA, roleB, roleC);

        model.addAttribute("groupList", groupList);

        model.addAttribute("userId", userId);

        return "search/search";
    }

    //グループの一件追加用メソッド
    @PostMapping("/addGroup")
    public String addGroup(@Validated @ModelAttribute Group group,
                           Model model, BindingResult result){
        group.setGroupId(group.getGroupId());
        group.setGroupName(group.getGroupName());
        group.setGroupRole(group.getGroupRole());
        group.setGroupBio(group.getGroupBio());

        model.addAttribute("groups",group);
        if(result.hasErrors()){
            return "error";
        }
        groupRepository.save(group);
        return "group_add_complete";
    }

    @GetMapping("/teacher/showCreateMenu")
        public String showCreateMenu(Model model){ return "group_add"; }

    //グループの一覧表示 　データはとってこれる　
    @GetMapping("/showGroupList")
//    @ResponseBody
    public String showGroupList(Model model,String id){
        model.addAttribute("groups",groupRepository.findByUser(id));
        return "group/showGroupList";
    }

    //一件取得用メソッド
    @GetMapping("/{id:[0-9]+")
    public String showGroup(Model model,@PathVariable("id") int groupId,HttpSession session){
        model.addAttribute("",groupRepository.findById(groupId));
        return "";
    }

    //グループを一件削除
    @PostMapping("/deleteOne/{id:.+")
    public String deleteGroupOne(@PathVariable String groupId){
        groupRepository.deleteById(Integer.parseInt(groupId));
        return "一件削除";
    }

    //グループのメンバー一覧表示
    @GetMapping("/showGroupMemberList/{groupId}")
    public String showGroupMemberList(Model model,
                                      @PathVariable("groupId") String groupId){

        System.out.println(groupId + "グループID");

        List<GroupMember> groupMembers= groupMemberRepository.findByGroupId(groupId);
        System.out.println(groupMembers);
        model.addAttribute("groupMembers",groupMembers);

        return "seminar/group_member_list";
    }

    //グループのタグを表示
    @GetMapping("/showGroupTag")
    public String showGroupTag(Model model,String groupId) {
        model.addAttribute("hoge", tagRepository.findByGroup(groupId));
        return "hoge";
    }

    //投稿一覧取得
    //グループ、ゼミのメインメニューを表示するためのメソッド
//    @GetMapping("/showGroupMessage/{groupId}")
//    public String showGroupMessage(Model model,@PathVariable("groupId") int groupId, Authentication loginUser){
    @PostMapping("/showGroupMessage")
    public String showGroupMessage(Model model, int groupId, Authentication loginUser){
        List<GroupMessage> groupMessagesList = groupMessageRepository.findByGroup(groupId);

        List<Group> group = groupRepository.findById(groupId);
        model.addAttribute("groupMessages",groupMessagesList);
        System.out.println(groupId);
        model.addAttribute("groupId",groupId);
        model.addAttribute("username",loginUser.getName());

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
    @PostMapping("/addGroupMessage")
    public String addGroupMessage(Model model,
                                  int groupId,
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
        model.addAttribute("groupId", groupMessage.getGroupId());


        return "forward:showGroupMessage";
    }


    //投稿削除
    @PostMapping("/deleteGroupMessage")
    public String deleteGroupMessage(@PathVariable int groupMessageId) {
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

    @PostMapping("/meeting")
    public String skyway(){
        return "/meeting_skyway/index.html";
    }
}
