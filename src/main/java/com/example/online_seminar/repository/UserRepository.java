package com.example.online_seminar.repository;

import com.example.online_seminar.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
