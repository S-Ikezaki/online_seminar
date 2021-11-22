package com.example.online_seminar.model;

import lombok.Data;

@Data
public class TagRequest {
    private int id;
    private int tag_id;
    private boolean delete_flag;
}
