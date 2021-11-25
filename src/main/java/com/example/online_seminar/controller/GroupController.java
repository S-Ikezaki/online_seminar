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
import java.util.concurrent.CopyOnWriteArrayList;

//HTTPを受け取るクラス
@RestController
@RequestMapping("groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    //一件追加用メソッド
    @PostMapping("/insert")
    public String insert(@RequestBody Group group){
        String result = "";
        if(groupService.insert(group)){
            result = "一件追加";
        }else{
            result = "追加失敗";
        }
        return result;
    }

    @GetMapping("/selectMany")
    public List<Group> selectMany(){
        //全件取得
        return groupService.selectMany();
    }

    //一件取得用メソッド
    @GetMapping("/selectOne/{id:[0-9]+")
    public Group selectone(@PathVariable("id") String groupId){
        //selectone実行
        return groupService.selectOne(groupId);
    }

    //一件更新メソッド
    @PostMapping("/updateOne")
    public String updateOne(@RequestBody Group group) {
        String result = "";
        if (groupService.updateOne(group)) {
            result = "一見更新";
        } else {
            result = "更新失敗";
        }
        return result;
    }

    //一件削除
    @PostMapping("/deleteOne/{id:.+")
    public String deleteOne(@PathVariable("id") String groupId){
        String result = "";
        if(groupService.deleteOne(groupId)){
            result = "一件削除";
        }else{
            result = "削除しました";
        }
        return result;
    }
}
