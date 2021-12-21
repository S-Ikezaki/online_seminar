package com.example.online_seminar.repository;

import com.example.online_seminar.entity.group.Group;

import com.example.online_seminar.entity.group.GroupMessage;
import com.example.online_seminar.entity.tag.Tag;
import com.example.online_seminar.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group,Long>{
    //名前で検索するメソッド
   /* @Query(name = "Group.findByName")
    List<Group> findByName(String name);*/

    //タグで検索するメソッド
    @Query(name = "Group.findByTagNq",nativeQuery = true)
    List<Group> findByTagNq(Tag tagName);

    @Query(name = "Group.findCompetitionNameByTagNq",nativeQuery = true)
    List<Group> findCompetitionByTag(Tag tagName);

    //削除用のグループ選択メソッド
    @Query(name = "Group.findByIdDeleteNq",nativeQuery = true)
    List<Group> findByIdDeleteNq(String groupId);

    //所属しているグループの表示
    @Query(name = "Group.findByUserNq",nativeQuery = true)
    List<Group> findByUser(String userId);

}
