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
    private String userId;

    @Column(name = "enterprise_id" ,nullable = false)
    private String enterpriseId;

//    @Column(name = "introduction" ,nullable = false)
//    private String introduction;  // 紹介文（いる？）


    @ManyToOne
<<<<<<< HEAD
    @JoinColumn(name = "user_id", referencedColumnName = "user_id",insertable = false, updatable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "enterprise_id",referencedColumnName = "enterprise_id",insertable = false, updatable=false)
=======
    @JoinColumn(name = "user_id", referencedColumnName = "user_id",insertable=false, updatable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "enterprise_id",referencedColumnName = "enterprise_id",insertable=false, updatable=false)
>>>>>>> 8af5cf9e324216bd399fd821adcfb762e7ee0d4a
    private Enterprise enterprise;
}
