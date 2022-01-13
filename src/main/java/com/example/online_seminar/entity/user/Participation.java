package com.example.online_seminar.entity.user;

import com.example.online_seminar.entity.group.Group;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "participation_mst")
public class Participation {
    @Id
    @Column(name = "participation_id", nullable = false)
    private String participationId;

    @JoinColumn(name = "create_user_id", nullable = false, referencedColumnName = "user_id")
    private String createUserId;

    @JoinColumn(name = "create_user_name", nullable = false, referencedColumnName = "user_name")
    private String createUserName;

    @JoinColumn(name = "group_id", nullable = false, referencedColumnName = "group_id")
    private String groupId;

    @JoinColumn(name = "address_user_id", nullable = false, referencedColumnName = "user_id")
    private String addressUserId;

    @Column(name = "participation_contents", nullable = false)
    private String participationContents;

    @Column(name = "create_datetime", nullable = false)
    private Date createDatetime;

//    @OneToOne
//    @JoinColumns({
//            @JoinColumn(name = "create_user_id", nullable = false, referencedColumnName = "user_id", updatable=false),
//            @JoinColumn(name = "address_user_id", nullable = false, referencedColumnName = "user_id", updatable=false)
//    })
//    private User user;

//    @OneToOne
//    @JoinColumn(name = "create_user_id", nullable = false, referencedColumnName = "user_id", updatable=false)
//    private User userS;
//
//    @OneToOne
//    @JoinColumn(name = "address_user_id", nullable = false, referencedColumnName = "user_id", updatable=false)
//    private User userT;

//    @ManyToOne
//    @JoinColumn(name = "group_id", nullable = false, referencedColumnName = "group_id", updatable=false)
//    private Group group;
}
