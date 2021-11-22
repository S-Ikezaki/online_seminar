package com.example.online_seminar.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SecondaryTable;

@Getter
@Setter
@Entity
public class Employee {
    @Id
    private String user_id;
    private String enterprise_id;
    private String introduction;
    private boolean delete_flag;

    @ManyToOne
    private User user;

    @ManyToOne
    private Enterprise enterprise;
}
