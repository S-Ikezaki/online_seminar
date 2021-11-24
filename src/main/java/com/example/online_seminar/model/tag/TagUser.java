package com.example.online_seminar.model.tag;

import com.example.online_seminar.model.tag.Tag;
import com.example.online_seminar.model.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class TagUser implements Serializable{
    @Id
    @NotBlank
    private int id;

    @Id
    @NotBlank
    private int tag_id;

    private String user_id;

    /*@ManyToOne
    private User user;

    @ManyToOne
    private Tag tag;*/
}
