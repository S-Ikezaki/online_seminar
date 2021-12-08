package com.example.online_seminar.entity.group;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Table(name = "meeting_member_mst")
public class MeetingMember{
    @Id
    @Column(name = "group_id")
    private String groupId;

    @Column(name = "user_name")
    private Date userName;

    @ManyToOne
<<<<<<< HEAD
    @JoinColumn(name = "group_id",referencedColumnName = "group_id",insertable = false, updatable=false)
=======
    @JoinColumn(name = "group_id",referencedColumnName = "group_id",insertable=false, updatable=false)
>>>>>>> 8af5cf9e324216bd399fd821adcfb762e7ee0d4a
    private Group group;

}

