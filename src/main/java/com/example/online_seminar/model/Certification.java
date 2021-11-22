package com.example.online_seminar.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

//認証のゲッターセッター
@Data
@Entity
public class Certification {
    @NotBlank
    @Id
    @GeneratedValue
    private String user_id;
    @NotBlank
    private String password;
    private boolean delete_flag;
}

