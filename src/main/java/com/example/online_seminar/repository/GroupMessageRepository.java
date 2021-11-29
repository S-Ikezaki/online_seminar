package com.example.online_seminar.repository;

import com.example.online_seminar.entity.group.GroupMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupMessageRepository extends JpaRepository<GroupMember,Long> {
}
