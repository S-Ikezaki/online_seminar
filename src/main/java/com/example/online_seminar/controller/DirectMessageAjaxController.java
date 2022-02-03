package com.example.online_seminar.controller;

import com.example.online_seminar.entity.user.DirectMessage;
import com.example.online_seminar.entity.user.User;
import com.example.online_seminar.repository.DirectMessageRepository;
import com.example.online_seminar.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.util.List;

@Controller
@RequestMapping("/dm/ajax")
public class DirectMessageAjaxController {

    // DMに必要な情報をまとめて管理（削除予定）
//    public User addressUser = new User();
//    public List<User> userList = new ArrayList<User>();
//    public List<User> searchUserList = new ArrayList<User>();
//    public List<DirectMessage> directMessages = new ArrayList<DirectMessage>();
//    public List<DirectMessage> directMessageList = new ArrayList<DirectMessage>();
//
    public final DirectMessageRepository directMessageRepository;
    public final UserRepository userRepository;
//
//    @Autowired
//    public DMService dmService;

    public DirectMessageAjaxController(
            DirectMessageRepository directMessageRepository,
            UserRepository userRepository
    ) {
        this.directMessageRepository = directMessageRepository;
        this.userRepository = userRepository;
    }

    // DMのやりとりのあるユーザを取得するメソッド
//    public List<User> getUserList(String userId) {
//
//        System.out.println("ここから");
//        System.out.println(directMessageList.get(0).getCreateUser().getUserName());
//        System.out.println(directMessageList.get(0).getAddressUser().getUserName());
//
//        List<User> users = new ArrayList<User>();
//        for(DirectMessage directMessage: directMessageList){
//            if(directMessage.getCreateUserId().equals(userId)){
//                if(!users.contains(directMessage.getAddressUser())){
//                    System.out.println("ここ！！！：" + directMessage.getAddressUser().getUserName());
//                    users.add(directMessage.getAddressUser());
////                    System.out.println(users.size());
//                    System.out.println("if1：ID" + users.get(users.size()-1).getUserId());
//                    System.out.println("if1：NAME" + users.get(users.size()-1).getUserName());
//                }
//            } else {
//                if(!users.contains(directMessage.getCreateUser())){
//                    users.add(directMessage.getCreateUser());
////                    System.out.println(users.size());
////                    System.out.println("if1：" + users.get(users.size()-1).getUserId());
////                    System.out.println("if2：" + users.get(users.size()-1).getUserName());
//                }
//            }
//        }
//        return users;
//    }

    // 指定された相手とのDMを取得
    @PostMapping("/show")
    public String showDirectMessagePerson(@RequestBody String addressUserId, Authentication user, Model model) {

        String userId = user.getName();

        DirectMessage directMessages = new DirectMessage();
        User addressUser = new User();

//        List<DirectMessage> directMessages = directMessageRepository.findDirectMessage(addressUserId,userId);
//        directMessages = dmService.FindDirectMessage(addressUserId,userId);
//        directMessages = directMessageRepository.findDirectMessage(addressUserId,userId);
//        User addressUser = userRepository.findByUserId(addressUserId);
//        addressUser = dmService.FindByUserId(addressUserId);
//        addressUser = userRepository.findByUserId(addressUserId);

        model.addAttribute("dms",directMessages);

        return "dm/direct_message";
    }

    // ユーザをキーワードで検索（名前）
    @PostMapping("/search")
    public String searchUser(@RequestBody String keyword, Authentication loginUser , Model model) {
//        List<User>
        String decodeKeyword = URLDecoder.decode(keyword);
        System.out.println(decodeKeyword.substring(decodeKeyword.indexOf("=") + 1));

        List<User> searchUserList = userRepository.findAllByUserNameLike("%" + decodeKeyword.substring(decodeKeyword.indexOf("=") + 1) + "%");

        System.out.println(searchUserList.size());

        searchUserList.removeIf(user -> user.getUserId().equals(loginUser.getName()));

        model.addAttribute("searchUsers",searchUserList);

        System.out.println("レスポンス前");

//        return searchUserList;
        return "dm/direct_message :: searchList";
    }

    @GetMapping("dm/ajax/input")
    public void test(String url){
    }

    // ダイレクトメッセージの送信
//    @PostMapping("/send")
//    public String sendMessage(@ModelAttribute("message") String message, BindingResult result, Authentication loginUser, Model model) {
//
//        if (result.hasErrors()){
//            return "login";
//        }
//        Calendar calendar = Calendar.getInstance();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String sdfCalender = sdf.format((calendar.getTime()));
//
//        String loginUserId = loginUser.getName();
//
//        DirectMessage directMessage = new DirectMessage();
//        directMessage.setDirectMessageId(0);
//        directMessage.setCreateUserId(loginUserId);
//        directMessage.setCreateUserName(userRepository.findByUserId(loginUserId).getUserName());
//        directMessage.setAddressUserId(addressUser.getUserId());
//        directMessage.setAddressUserName(addressUser.getUserName());
//        directMessage.setDirectMessageContent(message);
//        directMessage.setCreateDatetime(Date.valueOf(sdfCalender));
//
////        dmService.SaveDirectMessage(directMessage);
////        directMessageRepository.save(directMessage);
//
//        directMessageList.clear();
//        directMessageList = dmService.saveAndFindDM(directMessage, loginUserId);
////        directMessageList = dmService.FindAllByAddressUserIdOrCreateUserId(loginUserId);
////        directMessageList = directMessageRepository.findAllByAddressUserIdOrCreateUserId(loginUserId,loginUserId);
//        Collections.reverse(directMessageList);
//
//        directMessages = dmService.FindDirectMessage(addressUser.getUserId(),loginUserId);
////        directMessages = directMessageRepository.findDirectMessage(addressUser.getUserId(),loginUserId);
//
//
//
//        userList = new ArrayList<>();
//        userList = getUserList(loginUserId);
//
//        model.addAttribute("addressUser",addressUser);
//        model.addAttribute("users",userList);
//        model.addAttribute("searchUsers",searchUserList);
//        model.addAttribute("dms", directMessages);
//
//        return "dm/direct_message";
//    }

}
