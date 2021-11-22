package com.example.online_seminar.repository;

import com.example.online_seminar.model.group.MeetingChat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingChatRepository extends JpaRepository<MeetingChat,Long> {
}
