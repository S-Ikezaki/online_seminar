package com.example.online_seminar.model;

import lombok.Data;
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
public class DirectMessage {
    @NotBlank
    @Id
    private String direct_message_id;

    @Id
    private String create_user_id;

    private String create_user_name;

    @Id
    private String address_user_id;

    private String address_user_name;
    private String direct_message_content;
    private Date create_datetime;
    private boolean delete_flag;

    @ManyToOne
    private User user;
}
