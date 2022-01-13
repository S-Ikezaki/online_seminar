package com.example.online_seminar.entity.user;

import com.example.online_seminar.entity.tag.TagUser;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
//
//@Entity
//@Data
//@Table(name="user_mst")
//public class User implements Serializable {
//    @Id
//    @Column(name = "user_id", nullable = false)
//    private String userId;
//
//    @Column(name = "user_name", nullable = false)
//    private String userName;
//
//    @Column(name = "user_role", nullable = false)
//    private int userRole;
//
//    @OneToMany(mappedBy = "user")
//    List<Student> students;
//
//    @OneToMany(mappedBy = "user")
//    List<Teacher> teachers;
//
//    @OneToMany(mappedBy = "user")
//    List<Employee> employees;
//
//    @OneToMany(mappedBy = "user")
//    List<TagUser> tagUsers;
//
//    @OneToMany(mappedBy = "user")
//    List<Request> requests;
//
//    @OneToMany(mappedBy = "user")
//    //宛先が難しいです
//    List<DirectMessage> directMessages;
//
//    @OneToOne
//    @JoinColumn(name="user_id",referencedColumnName = "user_id",insertable = false, updatable=false)
//    private Certification certification;
//}
@Entity
@Data
@Table(name="user_mst")
public class User implements Serializable {
    @Id
    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "user_name", nullable = false)
    private String userName;

//    @Column(name = "user_role", nullable = false)
//    private int userRole;

    @OneToOne
    @JoinColumn(name="user_id",referencedColumnName = "user_id",insertable = false, updatable=false)
    Student students;

    @OneToOne
    @JoinColumn(name="user_id",referencedColumnName = "user_id",insertable = false, updatable=false)
    Teacher teachers;

    @OneToOne
    @JoinColumn(name="user_id",referencedColumnName = "user_id",insertable = false, updatable=false)
    Employee employees;

    @OneToMany
    @JoinColumn(name="user_id",referencedColumnName = "user_id",insertable = false, updatable=false)
    List<TagUser> tagUsers;

    @OneToMany
    @JoinColumn(name="user_id",referencedColumnName = "user_id",insertable = false, updatable=false)
    List<Request> requests;

    @OneToMany
    @JoinColumn(name="create_user_id",referencedColumnName = "user_id",insertable = false, updatable=false)
    //@JoinColumn(name="address_user_id",referencedColumnName = "user_id")
    //宛先が難しいです
    List<DirectMessage> directMessages;

    @OneToOne
    @JoinColumn(name="user_id",referencedColumnName = "user_id",insertable = false, updatable=false)
    private Certification certification;

//    @OneToOne
//    @JoinColumns({
//            @JoinColumn(name = "create_user_id", referencedColumnName = "user_id", updatable = false),
//            @JoinColumn(name = "address_user_id", referencedColumnName = "user_id", updatable = false)
//    })
//    private Participation participation;

//    @OneToOne
//    @JoinColumn(name = "create_user_id", referencedColumnName = "user_id", updatable = false)
//    private Participation parUserS;
//
//    @OneToOne
//    @JoinColumn(name = "address_user_id", referencedColumnName = "user_id", updatable = false)
//    private Participation parUserT;
}
