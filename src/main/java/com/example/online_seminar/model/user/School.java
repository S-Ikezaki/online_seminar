package com.example.online_seminar.model.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
public class School implements Serializable {
    @Id
    @NotBlank
    private String school_id;

    private String school_name;

    /*@OneToMany
    List<Teacher> teachers;

    @OneToMany
    List<Student> students;*/
}
