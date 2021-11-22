package com.example.online_seminar.model;

import lombok.Data;

import java.sql.Date;

@Data
public class File {
    private String file_id;
    private String file_name;
    private String file_pass;
    private String user_id;
    private String user_name;
    private Date create_datetime;
    private String group_id;
    private boolean delete_flag;
}
