package com.example.online_seminar.repository;

import com.example.online_seminar.model.group.Group;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository {

    //Groupテーブルにデータを1件insert
    public int insertOne(Group group) throws DataAccessException;

    //Groupテーブルのデータを1件取得
    public Group selectOne(String groupId) throws DataAccessException;

    //全データ取得
    public List<Group> selectMany() throws DataAccessException;

    //テーブル1件更新
    public int updateOne(Group group) throws DataAccessException;

    //テーブル1件削除
    public int deleteOne(String groupId) throws DataAccessException;
}
