package com.example.online_seminar.repository;
import com.example.online_seminar.model.group.Group;
import com.example.online_seminar.model.user.User;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository  {
    //userテーブルにデータを1件insert
    public int insertOne(User user) throws DataAccessException;
}
