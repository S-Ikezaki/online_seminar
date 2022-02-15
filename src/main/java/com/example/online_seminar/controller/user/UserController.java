package com.example.online_seminar.controller.user;

import com.example.online_seminar.entity.user.Certification;
import com.example.online_seminar.entity.user.User;
import com.example.online_seminar.repository.CertificationRepository;
import com.example.online_seminar.repository.GroupRepository;
import com.example.online_seminar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("users")
public class UserController {


    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


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

    //ユーザ一覧表示
    @GetMapping("/showUserList")
    @ResponseBody
    public String showUserList(Model model) {
        model.addAttribute("", userRepository.findAll());
        return "";
    }

    // 学生一覧表示
    @GetMapping("/showUserList/{role}")
    @ResponseBody
    public String showStudentList(Model model, @PathVariable("role") String role) {
        model.addAttribute("", userRepository.findStudentByRole(role));
        return "";
    }
/*
    // 教師一覧表示
    @GetMapping("/showUserList/{role}")
//    @ResponseBody
    //BeanCreationExceptionの原因になっている
    public String showTeacherList(Model model, @PathVariable("role") String role) {
        model.addAttribute("", userRepository.findTeacherByRole(role));
        return "";
    }
*/

    // ユーザ一件追加
    @PostMapping("/add")
    public String addUser(
            @ModelAttribute() String userName,
            @ModelAttribute() int userRole,
            Model model
    ){
        User user = new User();
        user.setUserId("12");
        user.setUserName(userName);
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

    //パスワード更新
    @GetMapping("/updatePass")
    public String updatePassword() {
        System.out.println("UserController");

        return "teacher/teacher_edit_password";
    }

    @PostMapping("/editPass")
    public  String editPassword(String password , Authentication loginUser) {

        String userId = loginUser.getName();

        String hashPass = passwordEncoder().encode(password);

        Certification certification = new Certification();

        int role = certificationRepository.findByUserId(userId).getRole();

        certification.setUserId(userId);
        certification.setPassword(hashPass);
        certification.setRole(role);

        certificationRepository.save(certification);

        return "teacher/teacher_edit_pass_complete";
    }

    // ユーザが所属しているグループを取得
    @RequestMapping("/")
    public String usersGroup(User user, Model model) {

//        List<Group> groups = groupRepository.findByUser(user);

//        model.addAttribute(groups);
        return "";
    }

    @GetMapping("/teacher/group_create")
    public String createGroup(){
        return "test1";
    }

}
