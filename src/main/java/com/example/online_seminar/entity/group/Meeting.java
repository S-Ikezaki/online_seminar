package com.example.online_seminar.entity.group;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "meeting_mst")
public class Meeting{
    @Id
    @Column(name = "group_id",nullable = false)
    private String groupId;

    @Column(name = "user_name")
    private String userName;

    @ManyToOne
<<<<<<< HEAD
    @JoinColumn(name = "group_id",referencedColumnName = "group_id",insertable = false, updatable=false)
=======
    @JoinColumn(name = "group_id",referencedColumnName = "group_id",insertable=false, updatable=false)
>>>>>>> 8af5cf9e324216bd399fd821adcfb762e7ee0d4a
    private Group group;

}
