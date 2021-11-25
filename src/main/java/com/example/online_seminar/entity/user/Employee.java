package com.example.online_seminar.entity.user;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Data
@Table(name = "employee_mst")
public class Employee implements Serializable {
    @Id
    @Column(name = "user_id" ,nullable = false)
    private long user_id;

    @Column(name = "enterprise_id" ,nullable = false)
    private long enterprise_id;

//    @Column(name = "introduction" ,nullable = false)
//    private String introduction;  // 紹介文（いる？）


   /* @ManyToOne
    private User user;

    @ManyToOne
    private Enterprise enterprise;*/
}
