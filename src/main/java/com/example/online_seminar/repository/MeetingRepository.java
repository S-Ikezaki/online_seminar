package com.example.online_seminar.repository;

import com.example.online_seminar.model.Group;
import com.example.online_seminar.model.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepository extends JpaRepository<Meeting,Long> {
}
