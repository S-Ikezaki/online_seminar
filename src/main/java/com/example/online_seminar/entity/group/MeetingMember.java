package com.example.online_seminar.entity.group;

import com.example.online_seminar.entity.user.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
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
    @JoinColumn(name = "group_id",referencedColumnName = "group_id",insertable=false, updatable=false)
    private Group group;

}

