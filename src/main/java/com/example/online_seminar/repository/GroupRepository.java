package com.example.online_seminar.repository;

import com.example.online_seminar.entity.group.Group;
import com.example.online_seminar.entity.tag.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group,Integer>{
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
    List<Group> findByIdDeleteNq(int groupId);

    //所属しているグループの表示
    @Query(name = "Group.findByUserNq",nativeQuery = true)
    List<Group> findByUser(String userId);

    @Query(name= "Group.findByIdNq",nativeQuery = true)
    List<Group> findById(int groupId);

    //タグとロールでグループを検索(途中)
    @Query(name = "Group.findByTagRoleNq", nativeQuery = true)
    List<Group> findByRoleNq(String keyword, int role, int role2, int role3);
}
