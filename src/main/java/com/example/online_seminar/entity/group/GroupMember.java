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
public class GroupMember {
    @Id
    @JoinColumn(name = "group_id",nullable = false,referencedColumnName = "group_id")
    private String groupId;
    @Id
    @JoinColumn(name = "user_id",nullable = false,referencedColumnName = "user_id")
    private String userId;

    @Column(name = "user_name",nullable = false)
    private Date userName;

    @Column(name = "group_role",nullable = false)
    private int groupRole = 0;

    @ManyToOne
    private Group group;

    @ManyToOne
    private User user;
}
