package com.example.online_seminar.repository;

import com.example.online_seminar.model.group.MeetingMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingMemberRepository extends JpaRepository<MeetingMember,Long> {
}
