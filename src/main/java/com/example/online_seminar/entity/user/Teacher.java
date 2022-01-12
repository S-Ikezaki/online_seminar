package com.example.online_seminar.entity.user;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@ToString(exclude = {"school", "user"})
@Entity
@Table(name = "teacher_mst")
public class Teacher implements Serializable {
    @Id
    @Column(name = "user_id" ,nullable = false)
    private String userId;

    @Column(name = "school_id" ,nullable = false)
    private String schoolId;


    @ManyToOne
    @JoinColumn(name = "school_id",referencedColumnName = "school_id",insertable = false, updatable=false)
    private School school;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "user_id",insertable = false, updatable=false)
    private User user;
}
