package com.example.online_seminar.model.user;

import com.example.online_seminar.model.user.School;
import com.example.online_seminar.model.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
public class Teacher {
    @Id
    @NotBlank
    private String user_id;

    private String school_id;
    private boolean permission_cd;
    private boolean delete_flag;

    @ManyToOne
    private School school;

    @ManyToOne
    private User user;
}
