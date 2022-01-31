package com.example.online_seminar.entity.group;

import com.example.online_seminar.key.MeetingMemberKey;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "meeting_member_mst")
@IdClass(value = MeetingMemberKey.class)
public class MeetingMember{
    @Id
    @Column(name = "group_id")
    private int groupId;

    @Id
    @Column(name = "user_name")
    private String userName;

    @ManyToOne
    @JoinColumn(name = "group_id",referencedColumnName = "group_id",insertable = false, updatable=false)
    private Group group;

}

