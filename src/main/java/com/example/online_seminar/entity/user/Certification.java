package com.example.online_seminar.entity.user;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

//認証のゲッターセッター
@Entity
@Data
@Table(name = "certification_mst")

public class Certification implements Serializable{
    @Id
    @Column(name = "user_id" ,nullable = false)
    private String userId;

    @Column(name = "password" ,nullable = false)
    private String password;

    private String role;
    private boolean active = true;

    /*@OneToOne
    private User user;*/
}

