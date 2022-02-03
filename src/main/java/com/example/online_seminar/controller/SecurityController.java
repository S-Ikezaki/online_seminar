package com.example.online_seminar.controller;

import com.example.online_seminar.controller.group.GroupController;
import com.example.online_seminar.entity.group.Group;
import com.example.online_seminar.entity.user.User;
import com.example.online_seminar.repository.GroupRepository;
import com.example.online_seminar.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class SecurityController {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String showMenu(Authentication loginUser, Model model) {
        String userId = loginUser.getName();
        System.out.println("{"+userId+"}");
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



}
