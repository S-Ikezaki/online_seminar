package com.example.online_seminar.model;

import lombok.Data;

import java.sql.Date;

@Data
public class Request {
    private String request_id;
    private String create_user_id;
    private String create_user_name;
    private String address_user_id;
    private String address_user_name;
    private String request_content;
    private Date request_datetime;
    private boolean delete_flag;
}