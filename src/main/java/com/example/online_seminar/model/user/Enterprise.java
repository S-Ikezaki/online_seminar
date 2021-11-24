package com.example.online_seminar.model.user;

import com.example.online_seminar.model.user.Employee;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class Enterprise implements Serializable {
    @Id
    @NotBlank
    private String enterprise_id;

    private String enterprise_name;

    /*@OneToMany
    private Employee employee;*/
}
