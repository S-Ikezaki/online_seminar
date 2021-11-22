package com.example.online_seminar.model.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Getter
@Setter
@Entity
public class Student {
    @Id
    @NotBlank
    private String user_id;

    private String school_id;
    private int school_grade;
    private Date graduation_date;
    private boolean delete_flag;

    @ManyToOne
    private Student student;

    @ManyToOne
    private User user;
}
