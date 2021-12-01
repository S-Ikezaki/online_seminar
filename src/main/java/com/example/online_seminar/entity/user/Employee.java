package com.example.online_seminar.entity.user;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "employee_mst")
public class Employee implements Serializable {
    @Id
    @Column(name = "user_id" ,nullable = false)
    private long userId;

    @Column(name = "enterprise_id" ,nullable = false)
    private long enterpriseId;

//    @Column(name = "introduction" ,nullable = false)
//    private String introduction;  // 紹介文（いる？）


    @ManyToOne
    private User user;

    @ManyToOne
    private Enterprise enterprise;
}
