package com.example.online_seminar.entity.user;

import com.example.online_seminar.entity.tag.TagUser;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name="user_mst")
public class User implements Serializable {
    @Id
    @Column(name = "user_id" ,nullable = false)
    private long userId;

    @Column(name = "user_name" ,nullable = false)
    private String userName;

    @Column(name = "user_role" ,nullable = false)
    private int userRole;

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
