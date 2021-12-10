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
    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "user_role", nullable = false)
    private int userRole;

    @OneToMany(mappedBy = "user")
    List<Student> students;

    @OneToMany(mappedBy = "user")
    List<Teacher> teachers;

    @OneToMany(mappedBy = "user")
    List<Employee> employees;

    @OneToMany(mappedBy = "user")
    List<TagUser> tagUsers;

    @OneToMany(mappedBy = "user")
    List<Request> requests;

    @OneToMany(mappedBy = "user")
    //宛先が難しいです
    List<DirectMessage> directMessages;

    @OneToOne
    @JoinColumn(name="user_id",referencedColumnName = "user_id",insertable = false, updatable=false)
    private Certification certification;
}
