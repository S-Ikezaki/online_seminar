package com.example.online_seminar.entity.user;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Table(name = "enterprise_mst")
public class Enterprise implements Serializable {
    @Id
    @Column(name = "enterprise_id" ,nullable = false)
    private long enterpriseId;

    @Column(name = "enterprise_name" ,nullable = false)
    private String enterpriseName;

    /*@OneToMany
    private Employee employee;*/
}
