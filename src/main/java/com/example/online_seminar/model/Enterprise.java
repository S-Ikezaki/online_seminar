package com.example.online_seminar.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
public class Enterprise {
    @Id
    @NotBlank
    private String enterprise_id;

    private String enterprise_name;
    private boolean delete_flag;

    @OneToMany
    private Employee employee;
}
