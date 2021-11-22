package com.example.online_seminar.model;

import lombok.Data;

@Data
public class Tag {
    private int tag_id;
    private String tag_name;
    private boolean delete_flag;
}
