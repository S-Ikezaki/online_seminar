package com.example.online_seminar.dao;

import com.example.online_seminar.model.group.Group;
import com.example.online_seminar.model.user.User;
import com.example.online_seminar.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository("GroupDao")
public class GroupDao implements GroupRepository {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public int insertOne(Group group) throws DataAccessException {
        //1件登録
        int rowNumber = jdbc.update(
                "insert into group_mst(group_id," + "group_name," + "role," + "group_bio," + "values (?,?,?,?)",
                group.getGroupId(), group.getGroupName(), group.getRole(), group.getGroupBio()
        );
                return rowNumber;
    }

    //Groupテーブルのデータを1件取得
    @Override
    public Group selectOne(String groupId) throws DataAccessException{
        //１件取得
        Map<String,Object> map =
                jdbc.queryForMap("SELECT * FROM group_mst" + "where group_id = ?",groupId);
        //結果返却用の変数
        Group group = new Group();
        //取得したデータを結果返却用の変数にセットしていく
        group.setGroupId((String)map.get("group_id"));
        group.setGroupName((String)map.get("group_name"));
        group.setRole((Integer) map.get("role"));
        group.setGroupBio((String)map.get("group_bio"));
        return group;
    }

    //全件取得
    @Override
    public List<Group> selectMany() throws DataAccessException{
        //group_mstテーブルのデータを全件取得
        List<Map<String,Object>> getList = jdbc.queryForList("SELECT * FROM group_mst");
        List<Group> groupList = new ArrayList<>();

        for (Map<String,Object> map : getList){
            Group group = new Group();
            group.setGroupId((String) map.get("group_id"));
            group.setGroupName((String) map.get("group_id"));
            group.setRole((Integer) map.get("group_id"));
            group.setGroupBio((String) map.get("group_id"));
            groupList.add(group);
        }
        return groupList;
    }
    // Groupテーブルを1件更新
    @Override
    public int updateOne(Group group) throws DataAccessException{
        int rowNumber = jdbc.update(
                "UPDATE group_mst" + "set" + "group_name = ?," + "role = ?," + "group_bio = ?" + "where group_id = ?",
                group.getGroupName(),group.getRole(),group.getGroupBio(),group.getGroupId());
        return rowNumber;
    }

    //１件削除
    @Override
    public int deleteOne(String groupId) throws DataAccessException {
        int rowNumber = jdbc.update("DELETE FROM group_mst where group_id = ?",groupId);
        return rowNumber;
    }
}
