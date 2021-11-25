package com.example.online_seminar.entity.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class Enterprise{
    @Id
    @NotBlank
    private String enterprise_id;

    private String enterprise_name;

    /*@OneToMany
    private Employee employee;*/
}
