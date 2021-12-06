package com.example.online_seminar.entity.group;

import com.example.online_seminar.entity.user.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
@Table(name = "group_member_mst")
public class GroupMember implements Serializable {
    @Id
    @Column(name = "group_id")
    private String groupId;

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_name",nullable = false)
    private Date userName;

    @Column(name = "group_role",nullable = false)
    private int groupRole = 0;

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "group_id")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;
}
