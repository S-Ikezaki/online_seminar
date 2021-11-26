package com.example.online_seminar.entity.user;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Data
@Table(name = "student_mst")
public class Student implements Serializable {
    @Id
    @Column(name = "user_id" ,nullable = false)
    private long userId;

    @Column(name = "school_id" ,nullable = false)
    private String schoolId;

    @Column(name = "school_grade" ,nullable = false)
    private int schoolGrade;

    @Column(name = "graduation_date" ,nullable = false)
    private Date graduationDate;

    /*@ManyToOne
    private Student student;

    @ManyToOne
    private User user;*/
}
