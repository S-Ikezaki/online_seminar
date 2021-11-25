package com.example.online_seminar.repository;
import com.example.online_seminar.entity.user.User;
import org.springframework.dao.DataAccessException;

public interface UserRepository  {
    //userテーブルにデータを1件insert
    public int insertOne(User user) throws DataAccessException;
}
