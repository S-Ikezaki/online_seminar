package com.example.online_seminar.model;

import lombok.Data;

@Data
public class Group {
    private String group_id;
    private String group_name;
    private int role;
    private String group_bio;
    private boolean delete_flag;
}
