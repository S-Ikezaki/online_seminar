package com.example.online_seminar.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

//認証のゲッターセッター
@Getter
@Setter
@Entity
public class Certification {
    @NotBlank
    @Id
    private String user_id;

    @NotBlank
    private String password;

    @NotBlank
    private boolean delete_flag;

    @OneToOne
    private User user;
}

