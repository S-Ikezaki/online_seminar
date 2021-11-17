package com.example.online_seminar.model;

import lombok.Data;

import java.sql.Date;

@Data
public class Student {
    private String user_id;
    private String school_id;
    private int school_grade;
    private Date graduation_date;
    private boolean delete_flag;
}
