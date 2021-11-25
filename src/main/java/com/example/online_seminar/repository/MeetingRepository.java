package com.example.online_seminar.repository;

import com.example.online_seminar.entity.group.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepository extends JpaRepository<Meeting,Long> {
}
