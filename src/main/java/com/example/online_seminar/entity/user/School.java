package com.example.online_seminar.entity.user;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "school_mst")
public class School implements Serializable {
    @Id
    @Column(name = "school_id" ,nullable = false)
    private String schoolId;

    @Column(name = "school_name")
    private String schoolName;

    @OneToMany(mappedBy = "school")
    @JoinColumn(name = "school_id",referencedColumnName = "school_id",insertable = false, updatable=false)
    List<Teacher> teacher;

    @OneToMany(mappedBy = "school")
    @JoinColumn(name = "school_id",referencedColumnName = "school_id",insertable = false, updatable=false)
    List<Student> student;
}
