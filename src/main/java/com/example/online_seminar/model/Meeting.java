package com.example.online_seminar.model;

import lombok.Data;

@Data
public class Meeting {
    private String meeting_id;
    private String group_id;
    private String user_name;
    private int status;
    private boolean delete_flag;
}
