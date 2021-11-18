package com.example.online_seminar.model;

import lombok.Data;

import java.sql.Date;

@Data
public class DirectMessage {
    private String direct_message_id;
    private String create_user_id;
    private String create_user_name;
    private String address_user_id;
    private String address_user_name;
    private String direct_message_content;
    private Date create_datetime;
    private boolean delete_flag;
}
