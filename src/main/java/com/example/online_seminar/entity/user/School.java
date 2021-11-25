package com.example.online_seminar.entity.user;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Table(name = "school_mst")
public class School implements Serializable {
    @Id
    @Column(name = "school_id" ,nullable = false)
    private long schoolId;

    @Column(name = "school_name")
    private String schoolName;

    /*@OneToMany
    List<Teacher> teachers;

    @OneToMany
    List<Student> students;*/
}
