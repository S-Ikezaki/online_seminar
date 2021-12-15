package com.example.online_seminar.controller;

import com.example.online_seminar.entity.group.Group;
import com.example.online_seminar.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class SecurityController {

    private final GroupRepository groupRepository;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String showMenu(Authentication loginUser, Model model) {

        List<Group> groupList = groupRepository.findAll();
        List<Group> seminar = new ArrayList<Group>();
        List<Group> competition = new ArrayList<Group>();

        for (Group group : groupList) {
            if (group.getGroupRole() == 0) {
                seminar.add(group);
            } else {
                competition.add(group);
            }
        }

        model.addAttribute("username", loginUser.getName());
        model.addAttribute("role", loginUser.getAuthorities());

        System.out.println(loginUser.getAuthorities());
        model.addAttribute("groups", groupRepository.findAll());

        System.out.println();
        model.addAttribute("seminars", seminar);
        model.addAttribute("competitions", competition);

        return "main_menu";


    }
}