package com.example.online_seminar.repository;

import com.example.online_seminar.entity.group.GroupMember;
import com.example.online_seminar.key.GroupMemberKeys;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupMemberRepository extends JpaRepository<GroupMember, GroupMemberKeys> {
    Optional<GroupMember> findByMemberId(Long userId);
}
