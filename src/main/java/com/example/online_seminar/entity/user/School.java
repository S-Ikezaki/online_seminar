package com.example.online_seminar.entity.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class School{
    @Id
    @NotBlank
    private String school_id;

    private String school_name;

    /*@OneToMany
    List<Teacher> teachers;

    @OneToMany
    List<Student> students;*/
}
