package com.example.online_seminar.controller.group;

import com.example.online_seminar.entity.group.*;
import com.example.online_seminar.entity.tag.Tag;
import com.example.online_seminar.entity.tag.TagRequest;
import com.example.online_seminar.entity.user.Certification;
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

    private final CertificationRepository certificationRepository;

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
    public GroupController(CertificationRepository certificationRepository,
                           GroupRepository groupRepository,
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
        this.certificationRepository = certificationRepository;
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

    //???????????????????????????
    @GetMapping("/apply")
    //search??????????????????????????????????????????groupRole??????????????????????????????????????????????????????????
    public String Transition(@RequestParam("groupRole") int role,
                             @RequestParam("groupId") String groupId,
                             @RequestParam("userId") String userId,
                             Certification certification,
                             Model model
    ) {

        //?????????
        System.out.println(role);
        System.out.println(groupId);
        System.out.println(userId);

        int userRole = certification.getRole();

        model.addAttribute("role", userRole);

        //??????????????????????????????????????????
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

    //??????????????????????????????????????????
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

        List<GroupMember> groupLeader = groupMemberRepository.findByGroupIdAndAndGroupRoleOrGroupRole(groupId, 2, 3);

        String userName = userRepository.findByUserId(userId).getUserName();

//        System.out.println(groupMember.size());
        System.out.println(groupLeader.size());

        //?????????????????????
        System.out.println(groupLeader);
//        System.out.println(groupMember);
        System.out.println(groupId);//????????????????????????????????????ID
        System.out.println(userId);//?????????????????????ID
//        System.out.println(groupMember.get(0).getUserName()); //???????????????????????????
        System.out.println(groupLeader.get(0).getUserId()); //??????????????????????????????ID
        System.out.println(comment);//?????????????????????????????????????????????

        participation.setCreateUserId(userId);
        participation.setCreateUserName(userName);
        participation.setGroupId(groupId);
        participation.setAddressUserId(groupLeader.get(0).getUserId());
        participation.setParticipationContents(comment);
        participation.setCreateDatetime(Date.valueOf(sdfCalender));

        if (result.hasErrors()) {
            return "error";
        }

        System.out.println(participation);

        participationRepository.save(participation);

        return "seminar/seminar_apply_complete";
    }

    // ????????????????????????
    @PostMapping("/apply/list")
    public String showApplyList(int groupId, Model model) {

        List<Participation> applyList = participationRepository.findByGroupId(groupId);

        model.addAttribute("applyList", applyList);
        model.addAttribute("groupId", groupId);


        return "group_apply_list";
    }

    // ??????????????????????????????
    @PostMapping("/apply/reply")
    public String replyApply(@RequestParam("create_user_id") String userId,
                             @RequestParam("apply_id") int applyId,
                             @RequestParam("reply") int reply,
                             @RequestParam("group_id") int groupId,
                             Model model) {

        if (reply == 0) {
            String userName = userRepository.findByUserId(userId).getUserName();
            int role = certificationRepository.findByUserId(userId).getRole();

            GroupMember groupMember = new GroupMember();

            groupMember.setGroupId(groupId);
            groupMember.setUserId(userId);
            groupMember.setUserName(userName);
            if(role == 0) {
                groupMember.setGroupRole(0);
            } else if (role == 1 || role == 3) {
                groupMember.setGroupRole(2);
            } else {
                groupMember.setGroupRole(1);
            }

            groupMemberRepository.save(groupMember);
        }

        participationRepository.deleteById(applyId);

        List<Participation> applyList = participationRepository.findByGroupId(groupId);

        model.addAttribute("applyList", applyList);

        return "group_apply_list";
    }

    //??????????????????????????????????????????
    @GetMapping("/request_seminar")
    public String RequestSeminar(@RequestParam("userId") String userId,
                                 Model model) {

        //?????????
        System.out.println("????????????");
        System.out.println(userId);

        model.addAttribute("requestUser", userId);

        return "student/request_send";
    }

    //?????????????????????????????????????????????????????????
    @PostMapping("/request_presentation")
    public String RequestPresentation(@RequestParam("requestUser") String userId,
                                      @RequestParam("comment") String comment,
                                      @RequestParam("tagName") String tagName,
                                      Request request,
                                      Tag tag,
                                      TagRequest tagRequest,
                                      BindingResult result) {

        //?????????
        System.out.println(userId);
        System.out.println(comment);
        System.out.println(tagName);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        User user = userRepository.findByUserId(userId);

        List<Tag> tagList = tagRepository.findAll();

        System.out.println(tagList.size());

        System.out.println(tagList.get(6).getTagName());

        System.out.println("for????????????");
        for (int i = 0; i < tagList.size(); i++) {
            System.out.println("for????????????: " + i);
            if (tagList.get(i).getTagName().equalsIgnoreCase(tagName)) {
                System.out.println("tagName????????????????????????");
                break;
            }
            if (!(tagList.get(i).getTagName().equals(tagName)) && i >= tagList.size()) {
                System.out.println("tagName????????????????????????");
                tag.setTagName(tagName);
                System.out.println(tag);
                tagRepository.save(tag);
                System.out.println("save??????");
            }
            System.out.println("for????????????: " + i);
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

    // ?????????????????????
    @GetMapping("/search_group")
    public String SearchGroup(@RequestParam("userId") String userId,
                              @RequestParam("role") String role,
                              Model model) {

        //?????????
        System.out.println("?????????????????????");
        System.out.println(userId);
        System.out.println(role);

        List<Group> groupList = new ArrayList<Group>();

        model.addAttribute("groups", groupList);
        model.addAttribute("userId", userId);

        return "search/search";
    }

    //?????????????????????????????????????????????
    @PostMapping("/requestDelete")
    public String requestDelete(@RequestParam("requestId") int requestId){

        //?????????
        System.out.println(requestId);

        tagRequestRepository.deleteByRequestId(requestId);

        requestRepository.deleteByRequestId(requestId);

        return "search/search";
    }

//    //??????????????????????????????
//    @PostMapping("/addGroup")
//    public String addGroup(){
//
//        return "group_add";
//    }

    //??????????????????????????????????????????
    @PostMapping("/search_group_detail")
    public String SearchGroupDetail(@RequestParam(value = "keyword", required = false) String keyword,
                                    @RequestParam(value = "seminar1", required = false) String checkBoxSem,
                                    @RequestParam(value = "seminar2", required = false) String checkBoxCompP,
                                    @RequestParam(value = "seminar3", required = false) String checkBoxCompS,
                                    @RequestParam(value = "checkBoxReq", required = false) String checkBoxReq,
                                    @RequestParam("userId") String userId,
                                    Model model) {
//      ????????????
        System.out.println(keyword);
        System.out.println(checkBoxSem);
        System.out.println(checkBoxCompP);
        System.out.println(checkBoxCompS);
        System.out.println(checkBoxReq);
        System.out.println(userId);

        if (Objects.equals(checkBoxReq, "request")) {
            System.out.println("???????????????????????????");
            List<Request> requestList = requestRepository.findAll();
            model.addAttribute("requestList", requestList);

            return "search/search";
        }

        if (checkBoxSem == null && checkBoxCompP == null && checkBoxCompS == null && keyword.equals("")) {
            System.out.println("null??????");
            model.addAttribute("userId", userId);
            return "search/search";
        }

        int roleA = 0;
        int roleB = 1;
        int roleC = 2;

        //???????????????
        //????????????
        if (checkBoxCompP == null && checkBoxCompS == null) {
            System.out.println("????????????");
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

    //???????????????????????????????????????
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

    //??????????????????HTML????????????????????????
    @GetMapping("/showCreateMenu")
    public String showCreateMenu(Model model) {
        return "group_add";
    }

    //?????????????????????????????????????????????
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

    //???????????????????????????????????????????????????????????????
    @PostMapping("/addUser")
    public String addUser(Authentication loginUser, GroupMember groupMember, Group group, Model model) {

        System.out.println("adduser");

        int group_role = 1;
        Group group_info = groupRepository.findByGroupNameAndGroupRole(group.getGroupName(), group.getGroupRole());

        System.out.println("group_member_role:" + group_role);

        System.out.println("groupMUId:" + group_info);
        System.out.println("userID:" + loginUser.getName());

        int group_Id = group_info.getGroupId();
        System.out.println("????????????ID" + group_Id);

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
            //System.out.println("??????????????????");
            return "seminar/seminar_menu";
        } else if (group.getGroupRole() == 1) {
            //System.out.println("??????????????????????????????");
            return "seminar/seminar_competition_presentation";
        } else if (group.getGroupRole() == 2) {
            //System.out.println("??????????????????????????????");
            return "seminar/seminar_competition_submission";
        } else {
            return "error";
        }
    }

    //??????????????????????????? ????????????????????????????????????
    @GetMapping("/showGroupList")
//    @ResponseBody
    public String showGroupList(Model model, String id) {
        model.addAttribute("groups", groupRepository.findByUser(id));
        return "group/showGroupList";
    }

    //???????????????????????????
    @GetMapping("/{id:[0-9]+")
    public String showGroup(Model model, @PathVariable("id") int groupId, HttpSession session) {
        model.addAttribute("", groupRepository.findById(groupId));
        return "";
    }

    //???????????????????????????
    @PostMapping("/deleteOne/{id:.+")
    public String deleteGroupOne(@PathVariable String groupId) {
        groupRepository.deleteById(Integer.parseInt(groupId));
        return "????????????";
    }

    //???????????????????????????????????????
    @PostMapping("/showGroupMemberList")
    public String showGroupMemberList(Model model,int groupId) {

        System.out.println(groupId + "????????????ID");
        List<GroupMember> groupMembers = groupMemberRepository.findByGroupId(groupId);
        System.out.println(groupMembers);
        model.addAttribute("groupId",groupId);
        model.addAttribute("groupMembers", groupMembers);

        return "seminar/group_member_list";
    }

    //??????????????????????????????
    @PostMapping("/deleteGroupMember")
    public String deleteGroupMember(Model model, int groupId, String userId){

        System.out.println("delete:"+groupId);
        System.out.println("delete:"+userId);

        groupMemberRepository.deleteByGroupIdAndUserId(groupId,userId);
        System.out.println("????????????");

        return "forward:/groups/showGroupMemberList";
    }

    //??????????????????????????????
    @GetMapping("/showGroupTag")
    public String showGroupTag(Model model, String groupId) {
        model.addAttribute("hoge", tagRepository.findByGroup(groupId));
        return "hoge";
    }

    //??????????????????
    //?????????????????????????????????????????????????????????????????????????????????
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

    //????????????????????????????????????
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


    //????????????
    @PostMapping("/deleteGroupMessage")
    public String deleteGroupMessage(@PathVariable int groupMessageId) {
        groupRepository.deleteById(groupMessageId);
        return "hoge";
    }

    //?????????????????????????????????????????????
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
        model.addAttribute("flg", "open"); // ?????????????????????????????????

        return "/meeting_skyway/meeting.html";
    }

    @PostMapping("/meeting/join/{groupId}")
    public String joinMeeting(@PathVariable int groupId, Model model, Authentication loginUser) {
//        @RequestParam(name = "peer_id") String peerId,

        User loginUserName = userRepository.findByUserId(loginUser.getName());

        model.addAttribute("userName", loginUserName.getUserName());
        model.addAttribute("groupId", groupId);
//        model.addAttribute("peerId", peerId);
        model.addAttribute("flg", "join"); // ?????????????????????????????????

        return "/meeting_skyway/meeting.html";
    }

}
