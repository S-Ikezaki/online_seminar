package com.example.online_seminar.entity.group;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "meeting_chat_mst")
public class MeetingChat{
    @Id
    @Column(name = "meeting_chat_id",nullable = false)
    private String meetingChatId;

    @Column(name = "meeting_chat_contents")
    private String meetingChatContents;

    @Column(name = "group_id",nullable = false)
    private String groupId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "meeting_chat_datetime")
    private String meetingChatDatetime;

    @OneToMany
    @JoinColumn(name = "group_id",referencedColumnName = "group_id",insertable = false, updatable=false)
    List<Group> groups;
}
