package com.example.online_seminar.model;

import lombok.Data;

@Data
public class TagGroup {
    private int id;
    private int tag_id;
    private String group_id;
    private boolean delete_flag;
}
