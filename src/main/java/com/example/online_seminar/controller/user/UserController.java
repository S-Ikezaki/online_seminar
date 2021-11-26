package com.example.online_seminar.controller;

import com.example.online_seminar.entity.user.User;
import com.example.online_seminar.repository.CertificationRepository;
import com.example.online_seminar.repository.GroupRepository;
import com.example.online_seminar.repository.UserRepository;
import org.dom4j.rule.Mode;
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

    public String usersGroup() {

    }

    // ユーザ一件追加
    @PostMapping("/add")
    public String addUser(
            @RequestParam() String userName,
            @RequestParam() int userRole,
            Model model
    ){
        User user = new User();
        user.setUserId();
        user.setUserName(userName);
        user.setUserRole(userRole);
        userRepository.save(user);

        model.addAttribute("user", user);
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

    // パスワード更新
    @PostMapping("updatepass")
    public String updatePassword(
            @RequestParam() long userId,
            @RequestParam() String password,
            Model model
    ) {
        certificationRepository.save()
    }

    public String usersGroup() {

    }

}
