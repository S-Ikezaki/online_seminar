package com.example.online_seminar.controller;

import com.example.online_seminar.model.group.Group;
import com.example.online_seminar.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@ComponentScan
@Controller
@RequestMapping("/groups")
public class GroupController {

    private static final String VIEW = "/groups";

    @Autowired
    private GroupService groupService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(){
        return VIEW;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView searchGroup(ModelAndView mav, @RequestParam("group_id") String groupId,
                                    @RequestParam("group_name") String groupName,@RequestParam("role") int role,
                                    @RequestParam("group_bio") String groupBio,@RequestParam("delete_flag") boolean deleteFlag){
        mav.setViewName(VIEW);
        mav.addObject("group_name", groupId);
        mav.addObject("group_name", groupName);
        mav.addObject("role",role);
        mav.addObject("groupBio", groupBio);
        mav.addObject("delete_flag", deleteFlag);
        List<Group> result = groupService.search(groupId,groupName,role,groupBio,deleteFlag);
        mav.addObject("result", result);
        return mav;
    }
}
