package com.example.online_seminar.controller.group;

import com.example.online_seminar.entity.group.*;
import com.example.online_seminar.entity.tag.Tag;
import com.example.online_seminar.entity.tag.TagRequest;
import com.example.online_seminar.entity.user.Participation;
import com.example.online_seminar.entity.user.Request;
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
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.SimpleDateFormat;
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

    private final TagRequestRepository tagRequestRepository;

    private final UserRepository userRepository;

    private final RequestRepository requestRepository;

    private final ParticipationRepository participationRepository;

    private final MeetingRepository meetingRepository;

    private final MeetingMemberRepository meetingMemberRepository;

    @Autowired
    public GroupController(GroupRepository groupRepository,
                           TagGroupRepository tagGroupRepository,
                           GroupMessageRepository groupMessageRepository,
                           GroupMemberRepository groupMemberRepository,
                           TagRepository tagRepository,
                           TagRequestRepository tagRequestRepository,
                           UserRepository userRepository,
                           RequestRepository requestRepository,
                           ParticipationRepository participationRepository,
                           MeetingRepository meetingRepository,
                           MeetingMemberRepository meetingMemberRepository) {
        this.groupRepository = groupRepository;
        this.tagGroupRepository = tagGroupRepository;
        this.groupMessageRepository = groupMessageRepository;
        this.groupMemberRepository = groupMemberRepository;
        this.tagRepository = tagRepository;
        this.tagRequestRepository = tagRequestRepository;
        this.userRepository = userRepository;
        this.requestRepository = requestRepository;
        this.participationRepository = participationRepository;
        this.meetingRepository = meetingRepository;
        this.meetingMemberRepository = meetingMemberRepository;
    }

    /*@GetMapping("/add")
    public String addGroup(@ModelAttribute Group group){
        return " ";
    }*/

    //参加申請画面に遷移
    @GetMapping("/apply")
    //searchの参加申請ボタンを押された時groupRoleを受け取りゼミかコンペで遷移先を分ける?
    public String Transition(@RequestParam("groupRole") int role,
                             @RequestParam("groupId") String groupId,
                             @RequestParam("userId") String userId,
                             Model model
    ) {

        //確認用
        System.out.println(role);
        System.out.println(groupId);
        System.out.println(userId);

        //ロールによって遷移先を分ける
        if (role == 0) {
            model.addAttribute("groupId", groupId);
            model.addAttribute("userId", userId);
            return "seminar/seminar_apply";
        } else {
            model.addAttribute("groupId", groupId);
            model.addAttribute("userId", userId);
            return "competition/apply";
        }
    }

    //申請ボタンを押された時の処理
    @GetMapping("/apply/execution")
    public String Execution(@RequestParam("groupId") int groupId,
                            @RequestParam("userId") String userId,
                            @RequestParam("comment") String comment,
                            Participation participation,
                            BindingResult result,
                            Model model,
                            Authentication loginUser) {

        System.out.println("aaa");
        System.out.println(groupId);
        System.out.println(userId);
        System.out.println(comment);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sdfCalender = sdf.format((calendar.getTime()));

        List<GroupMember> groupLeader = groupMemberRepository.findByGroupRoleNq(groupId);

        List<GroupMember> groupMember = groupMemberRepository.findByUserId(userId);

        System.out.println(groupLeader.size());

        //受け取り確認用
        System.out.println(groupLeader);
        System.out.println(groupMember);
        System.out.println(groupId);//学生が申請したグループのID
        System.out.println(userId);//申請した学生のID
        System.out.println(groupMember.get(0).getUserName()); //申請した学生の名前
        System.out.println(groupLeader.get(0).getUserId()); //申請を受け取る教師のID
        System.out.println(comment);//参加申請画面で書かれたコメント

        participation.setCreateUserId(userId);
        participation.setCreateUserName(groupMember.get(0).getUserName());
        participation.setGroupId(groupId);
        participation.setAddressUserId(groupLeader.get(0).getUserId());
        participation.setParticipationContents(comment);
        participation.setCreateDatetime(Date.valueOf(sdfCalender));

        if (result.hasErrors()) {
            return "error";
        }

        System.out.println(participation);

        participationRepository.save(participation);

        List<Group> groupList = groupRepository.findByUser(userId);  //参加しているグループの一覧表示
        List<Group> seminar = new ArrayList<Group>();
        List<Group> competition = new ArrayList<Group>();

        for (Group group : groupList) {
            if (group.getGroupRole() == 0) {
                seminar.add(group);
            } else {
                competition.add(group);
            }
        }

        User userName = userRepository.findByUserId(userId);

        model.addAttribute("userId", loginUser.getName());
        model.addAttribute("userName", userName);
        model.addAttribute("role", loginUser.getAuthorities());


        System.out.println(loginUser.getAuthorities());
        model.addAttribute("seminars", seminar);
        model.addAttribute("competitions", competition);

        return "main_menu";
    }

    //ゼミ作成リクエスト画面に遷移
    @GetMapping("/request_seminar")
    public String RequestSeminar(@RequestParam("userId") String userId,
                                 Model model) {

        //確認用
        System.out.println("動作確認");
        System.out.println(userId);

        model.addAttribute("requestUser", userId);

        return "student/request_send";
    }

    //リクエスト提示ボタンが押された時の処理
    @PostMapping("/request_presentation")
    public String RequestPresentation(@RequestParam("requestUser") String userId,
                                      @RequestParam("comment") String comment,
                                      @RequestParam("tagName") String tagName,
                                      Request request,
                                      Tag tag,
                                      TagRequest tagRequest,
                                      BindingResult result) {

        //確認用
        System.out.println(userId);
        System.out.println(comment);
        System.out.println(tagName);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        User user = userRepository.findByUserId(userId);

        List<Tag> tagList = tagRepository.findAll();

        System.out.println(tagList.size());

        System.out.println(tagList.get(6).getTagName());

        System.out.println("for確認：外");
        for (int i = 0; i < tagList.size(); i++) {
            System.out.println("for確認：内: " + i);
            if (tagList.get(i).getTagName().equalsIgnoreCase(tagName)) {
                System.out.println("tagNameが存在してるとき");
                break;
            }
            if (!(tagList.get(i).getTagName().equals(tagName)) && i >= tagList.size()) {
                System.out.println("tagNameが存在しないとき");
                tag.setTagName(tagName);
                System.out.println(tag);
                tagRepository.save(tag);
                System.out.println("save成功");
            }
            System.out.println("for確認：端: " + i);
        }

        tagList = tagRepository.findByTagName(tagName);

        System.out.println("userName:" + user.getUserName());
        System.out.println("tagName:" + tagList.get(0).getTagId());

        request.setRequestUserId(userId);
        request.setRequestUserName(user.getUserName());
        request.setRequestContent(comment);
        request.setRequestDatetime(sdf.format(calendar.getTime()));

//        if(result.hasErrors()){
//            return  "error";
//        }

        System.out.println(request);
        requestRepository.save(request);

        Request requestID = requestRepository.findByRequestDatetime(sdf.format(calendar.getTime()));

        tagRequest.setTagId(tagList.get(0).getTagId());
        tagRequest.setRequestId(requestID.getRequestId());

        System.out.println(tagRequest);
        tagRequestRepository.save(tagRequest);

        return "search/search";
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

    //ゼミ作成リクエストの１件削除用
    @PostMapping("/requestDelete")
    public String requestDelete(@RequestParam("requestId") int requestId){

        //確認用
        System.out.println(requestId);

        tagRequestRepository.deleteByRequestId(requestId);

        requestRepository.deleteByRequestId(requestId);

        return "search/search";
    }

//    //ゼミ作成画面への遷移
//    @PostMapping("/addGroup")
//    public String addGroup(){
//
//        return "group_add";
//    }

    //検索ボタンが押された時の処理
    @PostMapping("/search_group_detail")
    public String SearchGroupDetail(@RequestParam(value = "keyword", required = false) String keyword,
                                    @RequestParam(value = "seminar1", required = false) String checkBoxSem,
                                    @RequestParam(value = "seminar2", required = false) String checkBoxCompP,
                                    @RequestParam(value = "seminar3", required = false) String checkBoxCompS,
                                    @RequestParam(value = "checkBoxReq", required = false) String checkBoxReq,
                                    @RequestParam("userId") String userId,
                                    Model model) {
//      値確認用
        System.out.println(keyword);
        System.out.println(checkBoxSem);
        System.out.println(checkBoxCompP);
        System.out.println(checkBoxCompS);
        System.out.println(checkBoxReq);
        System.out.println(userId);

        if (Objects.equals(checkBoxReq, "request")) {
            System.out.println("リクエスト検索成功");
            List<Request> requestList = requestRepository.findAll();
            model.addAttribute("requestList", requestList);

            return "search/search";
        }

        if (checkBoxSem == null && checkBoxCompP == null && checkBoxCompS == null && keyword.equals("")) {
            System.out.println("null確認");
            model.addAttribute("userId", userId);
            return "search/search";
        }

        int roleA = 0;
        int roleB = 1;
        int roleC = 2;

        //ロール分け
        //ゼミのみ
        if (checkBoxCompP == null && checkBoxCompS == null) {
            System.out.println("ゼミのみ");
            roleA = 0;
            roleB = 0;
            roleC = 0;
        } else if (checkBoxSem == null && checkBoxCompS == null) {
            roleA = 1;
            roleB = 1;
            roleC = 1;
        } else if (checkBoxSem == null && checkBoxCompP == null) {
            roleA = 2;
            roleB = 2;
            roleC = 2;
        } else if (checkBoxCompS == null) {
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

        System.out.println(roleA);
        System.out.println(roleB);
        System.out.println(roleC);

        List<Group> groupList = groupRepository.findByRoleNq(keyword, roleA, roleB, roleC);

        model.addAttribute("groupList", groupList);

        model.addAttribute("userId", userId);

        return "search/search";
    }

    //グループ一件追加用メソッド
    @PostMapping("/addGroup")
    public String addGroup(@Validated @ModelAttribute Group group, Authentication loginUser, GroupMember groupMember,
                           Model model, BindingResult result) {

        group.setGroupId(group.getGroupId());
        group.setGroupName(group.getGroupName());
        group.setGroupRole(group.getGroupRole());
        group.setGroupBio(group.getGroupBio());

        System.out.println(group.getGroupId());
        System.out.println(group.getGroupName());

        model.addAttribute("groups", group);
        model.addAttribute("groupId",group.getGroupId());
        if (result.hasErrors()) {
            return "error";
        }

        groupRepository.save(group);

        return "forward:/groups/addUser";
    }

    //グループ作成HTMLを開くための処理
    @GetMapping("/showCreateMenu")
    public String showCreateMenu(Model model) {
        return "group_add";
    }

    //グループ専用のディレクトリ作成
    @PostMapping("/createDirectory")
    public static void createDirectory(Group group) {

        String pathName = "C:/groups/" + group.getGroupId();
        Path p = Paths.get(pathName);

        try {
            Files.createDirectories(p);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    //グループ作成時に作成者をグループに追加する
    @PostMapping("/addUser")
    public String addUser(Authentication loginUser, GroupMember groupMember, Group group, Model model) {

        System.out.println("adduser");

        int group_role = 1;
        Group group_info = groupRepository.findByGroupNameAndGroupRole(group.getGroupName(), group.getGroupRole());

        System.out.println("group_member_role:" + group_role);

        System.out.println("groupMUId:" + group_info);
        System.out.println("userID:" + loginUser.getName());

        int group_Id = group_info.getGroupId();
        System.out.println("グループID" + group_Id);

        groupMember.setGroupId(group_Id);
        groupMember.setUserId(loginUser.getName());
        groupMember.setGroupRole(group_role);

        User user = userRepository.findByUserId(loginUser.getName());
        groupMember.setUserName(user.getUserName());

        System.out.println("groupMUName:" + groupMember.getUserName());

        group.setGroupId(group_info.getGroupId());
        createDirectory(group);
        System.out.println(groupMember);


        groupMemberRepository.save(groupMember);
        model.addAttribute("groupId",group_Id);

        if (group.getGroupRole() == 0) {
            //System.out.println("ゼミメニュー");
            return "seminar/seminar_menu";
        } else if (group.getGroupRole() == 1) {
            //System.out.println("発表型コンペメニュー");
            return "seminar/seminar_competition_presentation";
        } else if (group.getGroupRole() == 2) {
            //System.out.println("提出型コンペメニュー");
            return "seminar/seminar_competition_submission";
        } else {
            return "error";
        }
    }

    //グループの一覧表示 　データはとってこれる　
    @GetMapping("/showGroupList")
//    @ResponseBody
    public String showGroupList(Model model, String id) {
        model.addAttribute("groups", groupRepository.findByUser(id));
        return "group/showGroupList";
    }

    //一件取得用メソッド
    @GetMapping("/{id:[0-9]+")
    public String showGroup(Model model, @PathVariable("id") int groupId, HttpSession session) {
        model.addAttribute("", groupRepository.findById(groupId));
        return "";
    }

    //グループを一件削除
    @PostMapping("/deleteOne/{id:.+")
    public String deleteGroupOne(@PathVariable String groupId) {
        groupRepository.deleteById(Integer.parseInt(groupId));
        return "一件削除";
    }

    //グループのメンバー一覧表示
    @PostMapping("/showGroupMemberList")
    public String showGroupMemberList(Model model,int groupId) {

        System.out.println(groupId + "グループID");
        List<GroupMember> groupMembers = groupMemberRepository.findByGroupId(groupId);
        System.out.println(groupMembers);
        model.addAttribute("groupId",groupId);
        model.addAttribute("groupMembers", groupMembers);

        return "seminar/group_member_list";
    }

    //グループのタグを表示
    @GetMapping("/showGroupTag")
    public String showGroupTag(Model model, String groupId) {
        model.addAttribute("hoge", tagRepository.findByGroup(groupId));
        return "hoge";
    }

    //投稿一覧取得
    //グループ、ゼミのメインメニューを表示するためのメソッド
//    @GetMapping("/showGroupMessage/{groupId}")
//    public String showGroupMessage(Model model,@PathVariable("groupId") int groupId, Authentication loginUser){
    @PostMapping("/showGroupMessage")
    public String showGroupMessage(Model model, int groupId, Authentication loginUser) {
        List<GroupMessage> groupMessagesList = groupMessageRepository.findByGroup(groupId);

        List<Group> group = groupRepository.findById(groupId);

        model.addAttribute("groupMessages", groupMessagesList);
        System.out.println("groupId:" + groupId);
        model.addAttribute("groupId", groupId);
        model.addAttribute("username", loginUser.getName());

        GroupMember groupMember = groupMemberRepository.findByGroupIdAndUserId(groupId, loginUser.getName());
        model.addAttribute("groupMember", groupMember);

        Meeting meeting = meetingRepository.findByGroupId(groupId);
        List<MeetingMember> meetingMembers = meetingMemberRepository.findAllByGroupId(groupId);

        model.addAttribute("meeting", meeting);
        model.addAttribute("meetingMembers", meetingMembers);
        model.addAttribute("groups", group);

        if (group.get(0).getGroupRole() == 0) {
            return "seminar/seminar_menu";
        } else if (group.get(0).getGroupRole() == 1) {
            return "seminar/seminar_competition_presentation";
        } else {
            return "seminar/seminar_competition_submission";
        }
    }

    //グループのメッセージ追加
    @PostMapping("/addGroupMessage")
    public String addGroupMessage(Model model,
                                  int groupId,
                                  GroupMessage groupMessage,
                                  Authentication loginUser,
                                  BindingResult result) {

        System.out.println("addGroup-GroupId:"+groupId);
        System.out.println("addGroup-GroupMessage:"+groupMessage);

        User loginUserName = userRepository.findByUserId(loginUser.getName());

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sdfCalender = sdf.format(calendar.getTime());
        int groupMessageId = 0;

        groupMessage.setGroupMessageId(groupMessageId);
        groupMessage.setUserId(loginUser.getName());
        groupMessage.setUserName(loginUserName.getUserName());
        groupMessage.setCreateDatetime(Date.valueOf(sdfCalender));
        groupMessage.setMessageContents(groupMessage.getMessageContents());
        groupMessage.setGroupId(groupId);

        if (result.hasErrors()) {
            return "error";
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
    public String updateStudentRole(@RequestBody int groupRole, Model model) {
        GroupMember groupMember = new GroupMember();
        groupMember.setGroupRole(groupRole);
        groupMemberRepository.save(groupMember);
        return "hoge";
    }

    @PostMapping("/meeting/{groupId}")
    public String skyway(@PathVariable int groupId, Model model, Authentication loginUser) {

        User loginUserName = userRepository.findByUserId(loginUser.getName());

        model.addAttribute("userName", loginUserName.getUserName());
        model.addAttribute("groupId", groupId);
        model.addAttribute("flg", "open"); // 会議の開始を示すフラグ

        return "/meeting_skyway/meeting.html";
    }

    @PostMapping("/meeting/join/{groupId}")
    public String joinMeeting(@PathVariable int groupId, Model model, Authentication loginUser) {
//        @RequestParam(name = "peer_id") String peerId,

        User loginUserName = userRepository.findByUserId(loginUser.getName());

        model.addAttribute("userName", loginUserName.getUserName());
        model.addAttribute("groupId", groupId);
//        model.addAttribute("peerId", peerId);
        model.addAttribute("flg", "join"); // 会議の参加を示すフラグ

        return "/meeting_skyway/meeting.html";
    }

}
