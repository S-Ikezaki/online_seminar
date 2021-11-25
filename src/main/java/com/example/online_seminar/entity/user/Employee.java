package com.example.online_seminar.entity.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class Employee{
    @Id
    private String user_id;
    private String enterprise_id;
    private String introduction;

   /* @ManyToOne
    private User user;

    @ManyToOne
    private Enterprise enterprise;*/
}
