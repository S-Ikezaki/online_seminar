package com.example.online_seminar.entity.user;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
@Data
@Entity
@Table(name = "teacher_mst")
public class Teacher implements Serializable {
    @Id
    @Column(name = "user_id" ,nullable = false)
    private String userId;

    @Column(name = "school_id" ,nullable = false)
    private String schoolId;

    @Column(name = "permission_cd" ,nullable = false)
    private boolean permissionCd;

    @Column(name = "role" ,nullable = false)
    private boolean role;


    @ManyToOne
    private School school;

    @ManyToOne
    private User user;
}
