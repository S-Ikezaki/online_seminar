package com.example.online_seminar.entity.user;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Data
@Table(name = "teacher_mst")
public class Teacher implements Serializable {
    @Id
    @Column(name = "user_id" ,nullable = false)
    private String user_id;

    @Column(name = "school_id" ,nullable = false)
    private String school_id;

    @Column(name = "permission_cd" ,nullable = false)
    private boolean permission_cd;

    /*@ManyToOne
    private School school;

    @ManyToOne
    private User user;*/
}
