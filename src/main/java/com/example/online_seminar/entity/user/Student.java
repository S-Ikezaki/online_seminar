package com.example.online_seminar.entity.user;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Data
@Table(name = "student_mst")
public class Student implements Serializable {
    @Id
    @Column(name = "user_id" ,nullable = false)
    private String userId;

    @Column(name = "school_id" ,nullable = false)
    private String schoolId;

    @Column(name = "school_grade" ,nullable = false)
    private int schoolGrade;

    @Column(name = "graduation_date" ,nullable = false)
    private Date graduationDate;

    @ManyToOne
    @JoinColumn(name = "school_id",referencedColumnName = "school_id",insertable = false, updatable=false)
    private School school;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "user_id",insertable=false, updatable=false)
    private User user;
}
