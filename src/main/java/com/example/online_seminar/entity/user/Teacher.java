package com.example.online_seminar.entity.user;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "teacher_mst")
public class Teacher implements Serializable {
    @Id
    @Column(name = "user_id" ,nullable = false)
    private String userId;

    @Column(name = "school_id" ,nullable = false)
    private String schoolId;

    @Column(name = "permission_cd" ,nullable = false)
    private boolean teacherRole;


    @ManyToOne
<<<<<<< HEAD
    @JoinColumn(name = "school_id",referencedColumnName = "school_id",insertable = false, updatable=false)
    private School school;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "user_id",insertable = false, updatable=false)
=======
    @JoinColumn(name = "school_id",referencedColumnName = "school_id",insertable=false, updatable=false)
    private School school;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "user_id",insertable=false, updatable=false)
>>>>>>> 8af5cf9e324216bd399fd821adcfb762e7ee0d4a
    private User user;
}
