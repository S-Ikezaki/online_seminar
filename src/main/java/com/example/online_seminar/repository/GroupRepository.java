package com.example.online_seminar.repository;

import com.example.online_seminar.entity.group.Group;

import com.example.online_seminar.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group,Long>{
    //名前で検索するメソッド
    Optional<Group> findByName(String name);

    //ユーザーで検索するメソッド
    List<Group> findByUser(User user);

}
