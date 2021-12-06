package com.example.online_seminar.entity.user;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "enterprise_mst")
public class Enterprise implements Serializable {
    @Id
    @Column(name = "enterprise_id" ,nullable = false)
    private String enterpriseId;

    @Column(name = "enterprise_name" ,nullable = false)
    private String enterpriseName;

    @OneToMany
    private Employee employee;
}
