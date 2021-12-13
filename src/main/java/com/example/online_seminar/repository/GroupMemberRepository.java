package com.example.online_seminar.repository;

import com.example.online_seminar.entity.group.GroupMember;
import com.example.online_seminar.key.GroupMemberKeys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GroupMemberRepository extends JpaRepository<GroupMember, GroupMemberKeys> {
}
