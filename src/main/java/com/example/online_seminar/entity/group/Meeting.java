package com.example.online_seminar.entity.group;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "meeting_mst")
public class Meeting implements Serializable {
    @Id
    @Column(name = "group_id",nullable = false)
    private int groupId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "peer_id")
    private String peerId;

    @OneToOne
    @JoinColumn(name = "group_id",referencedColumnName = "group_id",insertable = false, updatable=false)
    private Group group;

    @OneToMany(mappedBy = "meeting")
    List<MeetingChat> meetingChats;

}
