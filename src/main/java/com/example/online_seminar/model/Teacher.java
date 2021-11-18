package com.example.online_seminar.model;

import lombok.Data;

@Data
public class Teacher {
    private String user_id;
    private String school_id;
    private boolean permission_cd;
    private boolean delete_flag;
}
