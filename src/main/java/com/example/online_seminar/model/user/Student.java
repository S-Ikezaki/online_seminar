package com.example.online_seminar.model.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@Entity
public class Student implements Serializable {
    @Id
    @NotBlank
    private String user_id;

    private String school_id;
    private int school_grade;
    private Date graduation_date;

    /*@ManyToOne
    private Student student;

    @ManyToOne
    private User user;*/
}
