package com.example.online_seminar.entity.group;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@Entity
public class MeetingMember{
    @Id
    @NotBlank
    private String meeting_id;
    @Id
    @NotBlank
    private String user_id;

    private Date user_name;

    /*@ManyToOne
    private Group group;

    @ManyToOne
    private User user;*/
}

