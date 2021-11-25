package com.example.online_seminar.entity.user;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Table(name = "teacher_mst")
public class Teacher implements Serializable {
    @Id
    @Column(name = "user_id" ,nullable = false)
    private String userId;

    @Column(name = "school_id" ,nullable = false)
    private String schoolId;

    @Column(name = "role" ,nullable = false)
    private boolean role;

    /*@ManyToOne
    private School school;

    @ManyToOne
    private User user;*/
}
