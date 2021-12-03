package com.example.online_seminar.controller.user;

import com.example.online_seminar.entity.user.Certification;
import com.example.online_seminar.entity.user.User;
import com.example.online_seminar.repository.CertificationRepository;
import com.example.online_seminar.repository.GroupRepository;
import com.example.online_seminar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("users")
public class UserController {

    private final UserRepository userRepository;
    private final CertificationRepository certificationRepository;
    private final GroupRepository groupRepository;

    // コンストラクタ
    @Autowired
    public UserController(
            UserRepository userRepository,
            CertificationRepository certificationRepository ,
            GroupRepository groupRepository
    ){
        this.userRepository = userRepository;
        this.certificationRepository = certificationRepository;
        this.groupRepository = groupRepository;
    }

    long userId;
//    long userId = repository.findById();



    @GetMapping("/")
    public String ownInfo(Model model) {
        model.addAttribute("",userRepository.findById(userId));
        return "";
    }

    // ユーザ一件追加
    @PostMapping("/add")
    public String addUser(
            @ModelAttribute() String userName,
            @ModelAttribute() int userRole,
            Model model
    ){
        User user = new User();
        user.setUserId(12);
        user.setUserName(userName);
        user.setUserRole(userRole);
        userRepository.save(user);

        model.addAttribute("user", user);
        return "";
    }

    // ユーザ一件削除
    @GetMapping("/delete/{id}")
    public String deleteUser(
            @PathVariable("id") long userId,
            Model model
    ) {
        userRepository.deleteById(userId);
        return "";
    }

//    @RequestMapping()
    // ユーザ（卒業した学生）複数削除
//    public String deleteMultiUser(){
//        Date date = new Date();
//    }

    // パスワード更新
    @PostMapping("updatepass")
    public String updatePassword(
            @ModelAttribute() long userId,
            @ModelAttribute() String password,
            Model model
    ) {
        Certification certification = new Certification();
        certification.setUserId(userId);
        certification.setPassword(password);
        certificationRepository.save(certification);

        return "";
    }

    // ユーザが所属しているグループを取得
    @RequestMapping("/")
    public String usersGroup(User user, Model model) {

//        List<Group> groups = groupRepository.findByUser(user);

//        model.addAttribute(groups);
        return "";
    }

}
