package com.example.online_seminar.repository;

import com.example.online_seminar.entity.user.User;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    //userテーブルにデータを1件insert
    public int insertOne(User user) throws DataAccessException;


}
