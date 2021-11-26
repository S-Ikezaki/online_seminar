package com.example.online_seminar.repository;

import com.example.online_seminar.entity.group.Group;
import com.example.online_seminar.entity.group.GroupMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupMemberRepository extends JpaRepository<GroupMember,Long> {
    Optional<GroupMember> findByMemberId(Long userId);
}
