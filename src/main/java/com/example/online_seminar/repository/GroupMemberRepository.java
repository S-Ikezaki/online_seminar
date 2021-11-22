package com.example.online_seminar.repository;

import com.example.online_seminar.model.Group;
import com.example.online_seminar.model.GroupMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupMemberRepository extends JpaRepository<GroupMember,Long> {
}
