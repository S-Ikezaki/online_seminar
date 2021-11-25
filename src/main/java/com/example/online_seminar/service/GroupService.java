package com.example.online_seminar.service;

import com.example.online_seminar.dao.GroupDao;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.online_seminar.model.group.Group;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class GroupService {

    @Autowired
    @Qualifier("GroupDao")
    GroupDao dao;

    //1件追加用メソッド
    public boolean insert(Group group){
        //insert実行
        int rowNumber = dao.insertOne(group);
        //判定用変数
        boolean result = false;
        if(rowNumber > 0){
            //insert成功
            result = true;
        }
        return result;
    }

    //全件取得用メソッド
    public List<Group> selectMany(){
        //selectMany実行
        return dao.selectMany();
    }


    // 1件取得用メソッド
    public Group selectOne(String groupId) {
        // selectOne実行
        return dao.selectOne(groupId);
    }

    //1件取得用メソッド
    public boolean updateOne(Group group){
        //1件更新
        int rowNumber = dao.updateOne(group);

        boolean result = false;
        if(rowNumber > 0){
            //insert成功
            result = true;
        }
        return result;
    }
    // 1件削除メソッド
    public boolean deleteOne(String groupId) {
        // 1件削除
        int rowNumber = dao.deleteOne(groupId);
        // 判定用変数
        boolean result = false;
        if (rowNumber > 0) {
            // delete成功
            result = true;
        }
        return result;

    }
}

