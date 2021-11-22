package com.example.online_seminar.model;

import lombok.Data;

@Data
public class Employee {
    private String user_id;
    private String enterprise_id;
    private String introduction;
    private boolean delete_flag;
}
