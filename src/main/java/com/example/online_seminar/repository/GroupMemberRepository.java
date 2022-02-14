package com.example.online_seminar.repository;

import com.example.online_seminar.entity.group.GroupMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GroupMemberRepository extends JpaRepository<GroupMember, Integer> {

    List<GroupMember> findByGroupId(int groupId);

    @Query(name = "Member.findByGroupRoleNq", nativeQuery = true)
    List<GroupMember> findByGroupRoleNq(int id);

    List<GroupMember> findByUserId(String userId);

    List<GroupMember> findByGroupIdAndAndGroupRoleOrGroupRole(int groupId, int role, int role2);

    GroupMember findByGroupIdAndUserId(int groupId, String userId);

    @Transactional
    List<GroupMember> deleteByGroupIdAndUserId(int groupId, String userId);

}
