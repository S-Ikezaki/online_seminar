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
public class GroupMessage {
    @Id
    @NotBlank
    private String group_message_id;

    @Id
    @NotBlank
    private String group_id;

    private Date create_datetime;
    private String message_contents;


    /*@ManyToOne
    private Group group;*/
}
