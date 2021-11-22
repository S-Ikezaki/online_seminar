package com.example.online_seminar.repository;

import com.example.online_seminar.model.Group;
import com.example.online_seminar.model.GroupMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupMessageRepository extends JpaRepository<GroupMessage,Long> {
}
