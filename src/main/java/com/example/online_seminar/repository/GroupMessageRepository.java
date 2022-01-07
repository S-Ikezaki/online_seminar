package com.example.online_seminar.repository;

import com.example.online_seminar.entity.group.Group;
import com.example.online_seminar.entity.group.GroupMember;
import com.example.online_seminar.entity.group.GroupMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupMessageRepository extends JpaRepository<GroupMessage, Long> {

    //グループごとのメッセージを表示するためのメソッド
    @Query(name = "Message.findByGroupNq", nativeQuery = true)
    List<GroupMessage> findByGroup(String groupId);

    //メッセージ投稿
    @Query(name = "Message.insert", nativeQuery = true)
    List<GroupMessage> insertGroupMessage(String userId, String userName, String content, String groupId);
}
