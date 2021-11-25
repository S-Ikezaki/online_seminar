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
@Table(name = "enterprise_mst")
public class Enterprise implements Serializable {
    @Id
    @Column(name = "enterprise_id" ,nullable = false)
    private long enterprise_id;

    @Column(name = "enterprise_name" ,nullable = false)
    private String enterprise_name;

    /*@OneToMany
    private Employee employee;*/
}
