package com.example.online_seminar.repository;

import com.example.online_seminar.model.DirectMessage;
import com.example.online_seminar.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectMessageRepository extends JpaRepository<DirectMessage,Long> {
}
