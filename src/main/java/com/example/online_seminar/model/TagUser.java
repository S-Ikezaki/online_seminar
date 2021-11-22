package com.example.online_seminar.model;

import lombok.Data;

@Data
public class TagUser {
    private int id;
    private int tag_id;
    private String user_id;
    private boolean delete_flag;
}
