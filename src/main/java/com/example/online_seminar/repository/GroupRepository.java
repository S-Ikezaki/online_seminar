package com.example.online_seminar.repository;

import com.example.online_seminar.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
//@Service
public class GroupRepository {

    private final JdbcTemplate jdbc;

    @Autowired
    public GroupRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }


    //select文用のprepared後で修正してこちらに変える
//    public ResultSet getAll(final Group g){
//        String sql = "select * from group_mst where group_id = ?";
//        return jdbc.execute(sql, new PreparedStatementCallback<ResultSet>() {
//            @Override
//            public ResultSet doInPreparedStatement(PreparedStatement ps)
//                    throws SQLException, DataAccessException {
//
//                ps.setString(1, g.getGroup_id());
//
//                List<Map<String, Object>> groupList = jdbc.queryForList(sql);
//                List<Group> list = new ArrayList<>();
//                for (Map<String, Object> str1 : groupList) {
//                Group group = new Group();
//                group.setGrooup_name((String)str1.get("grooup_name"));
//                group.setGroup_bio((String)str1.get("group_bio"));
//                list.add(group);
//        }
//
//                return ps.executeQuery();
//            }
//        });
//    }

    //group_mstにselect文のクエリを入力し、取り出した値をlistに代入する
    public List<Group> getAll() {
        //SQLの定義
        String sql = "select * from group_mst where group_id = 'S0000'";
        List<Map<String, Object>> groupList = jdbc.queryForList(sql);
        List<Group> list = new ArrayList<>();
        for (Map<String, Object> str1 : groupList) {
            Group group = new Group();
            group.setGrooup_name((String) str1.get("grooup_name"));
            group.setGroup_bio((String) str1.get("group_bio"));
            list.add(group);
        }
        return list;
    }
}