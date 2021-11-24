package com.example.online_seminar.model.user;

import com.example.online_seminar.model.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

//認証のゲッターセッター
@Getter
@Setter
@Entity
public class Certification implements Serializable {
    @NotBlank
    @Id
    private String user_id;

    @NotBlank
    private String password;

    /*@OneToOne
    private User user;*/
}

