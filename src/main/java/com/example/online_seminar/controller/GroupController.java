package com.example.online_seminar.controller;

import com.example.online_seminar.model.group.Group;
import com.example.online_seminar.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("groups")
public class GroupController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    final List<Group> getGroup(){
        return jdbcTemplate.query("select * from group_mst",(rs,i) -> {
            Group group = new Group();
            group.setGroupId(rs.getString("group_id"));
            return group;
        });
    }

    @RequestMapping(method = RequestMethod.POST)
    public Group postGroup(@RequestBody Group group){
        jdbcTemplate.update("insert into group_mst values ('1111','sampleSeminar',0,'サンプルのゼミです',0)",group.getGroupId());
        return group;
    }
}
