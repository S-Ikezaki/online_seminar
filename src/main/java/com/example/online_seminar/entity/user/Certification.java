package com.example.online_seminar.entity.user;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

//認証のゲッターセッター
@Entity
@Data
@Table(name = "certification_mst")
public class Certification implements Serializable {
    @Id
    @Column(name = "user_id" ,nullable = false)
    private long userId;

    @Column(name = "password" ,nullable = false)
    private String password;

    /*@OneToOne
    private User user;*/
}
