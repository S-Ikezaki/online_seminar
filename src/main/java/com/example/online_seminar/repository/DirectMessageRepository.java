package com.example.online_seminar.repository;

import com.example.online_seminar.model.user.DirectMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectMessageRepository extends JpaRepository<DirectMessage,Long> {
}
