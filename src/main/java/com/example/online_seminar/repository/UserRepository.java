package com.example.online_seminar.repository;
import com.example.online_seminar.entity.group.Group;
import com.example.online_seminar.entity.user.User;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository  extends JpaRepository<User,Long> {
    //グループで検索するメソッド
    List<User> findByGroup(Group group);
}
