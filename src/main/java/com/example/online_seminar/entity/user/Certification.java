package com.example.online_seminar.entity.user;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

//認証のゲッターセッター
@Entity
@Data
@Table(name = "certification_mst")
public class Certification implements Serializable{
    @Id
    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private int role;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id",insertable = false, updatable=false)
    private User user;

}

