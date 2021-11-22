package com.example.online_seminar.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@Entity
public class User {
    @Id
    @NotBlank
    private String user_id;

    private String user_name;
    private int user_role;
    private boolean delete_flag;

    @OneToMany
    List<Student> students;

    @OneToMany
    List<Teacher> teachers;

    @OneToMany
    List<Employee> employees;

    @OneToMany
    List<TagUser> tagUsers;

    @OneToMany
    List<Request> requests;

    @OneToMany
    List<DirectMessage> directMessages;

    @OneToOne
    private Certification certification;
}
