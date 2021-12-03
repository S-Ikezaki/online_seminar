package com.example.online_seminar.repository;

import com.example.online_seminar.entity.group.Group;

import com.example.online_seminar.entity.tag.Tag;
import com.example.online_seminar.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group,Long>{
    //名前で検索するメソッド
    @Query(name = "Group.findByNameVariable")
    List<Group> findByName(String name);

    //タグで検索するメソッド
    @Query(name = "Group.findByTagNqVariable",nativeQuery = true)
    List<Group> findByTagNq(Tag tagName);

}
