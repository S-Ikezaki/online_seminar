package com.example.online_seminar.model;

import lombok.Data;

import java.sql.Date;

@Data
public class GroupMessage {
    private String group_message_id;
    private String user_id;
    private Date create_datetime;
    private String message_contents;
    private String group_id;
    private boolean delete_flag;
}
