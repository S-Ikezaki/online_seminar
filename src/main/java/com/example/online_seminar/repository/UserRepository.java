package com.example.online_seminar.repository;

import com.example.online_seminar.entity.group.Group;
import com.example.online_seminar.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface UserRepository  extends JpaRepository<User,Long> {
    //グループで検索するメソッド
    List<User> findByGroup(Group group);

    @Query(name = "User.searchByTagNqVariable",nativeQuery = true)
    Collection<User> searchByTagNqVariable(String Tag);
}
