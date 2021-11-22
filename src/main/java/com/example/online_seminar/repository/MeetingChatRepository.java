package com.example.online_seminar.repository;

import com.example.online_seminar.model.Group;
import com.example.online_seminar.model.MeetingChat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingChatRepository extends JpaRepository<MeetingChat,Long> {
}
