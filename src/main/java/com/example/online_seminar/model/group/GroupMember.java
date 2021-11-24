package com.example.online_seminar.model.group;

import com.example.online_seminar.model.group.Group;
import com.example.online_seminar.model.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Getter
@Setter
@Entity
public class GroupMember {
    @Id
    @NotBlank
    private String group_id;
    @Id
    @NotBlank
    private String user_id;

    private Date user_name;
    private String group_role;

    @ManyToOne
    private Group group;

    @ManyToOne
    private User user;
}
