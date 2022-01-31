package com.example.online_seminar.repository;

import com.example.online_seminar.entity.group.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface MeetingRepository extends JpaRepository<Meeting, Integer> {

    Meeting findByGroupId(int groupId);

    @Transactional
    void deleteByGroupId(int groupId);
}
