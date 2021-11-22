package com.example.online_seminar.model;

import lombok.Data;

import java.sql.Date;

@Data
public class GroupMember {
    private String group_id;
    private String user_id;
    private Date user_name;
    private String group_role;
    private boolean delete_flag;
}
