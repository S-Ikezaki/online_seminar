package com.example.online_seminar.entity.user;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@Entity
@Data
@Table(name = "student_mst")
public class Student implements Serializable {
    @Id
    @Column(name = "user_id" ,nullable = false)
    private long user_id;

    @Column(name = "school_id" ,nullable = false)
    private String school_id;

    @Column(name = "school_grade" ,nullable = false)
    private int school_grade;

    @Column(name = "graduation_date" ,nullable = false)
    private Date graduation_date;

    /*@ManyToOne
    private Student student;

    @ManyToOne
    private User user;*/
}
