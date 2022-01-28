package com.example.online_seminar.repository;

import com.example.online_seminar.entity.group.MeetingMember;
import com.example.online_seminar.key.MeetingMemberKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MeetingMemberRepository extends JpaRepository<MeetingMember, MeetingMemberKey> {

    List<MeetingMember> findAllByGroupId(int groupId);

    int countByGroupId(int groupId);

    @Transactional
    void deleteAllByGroupId(int groupId);

    @Transactional
    void deleteByUserName(String userName);
}
