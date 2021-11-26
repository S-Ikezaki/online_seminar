package com.example.online_seminar.entity.tag;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Data
@Table(name = "tag_user")
public class TagUser{
    @Id
    @JoinColumn(name = "tag_id",referencedColumnName = "tag_id")
    private int tagId;

    @Id
    @JoinColumn(name = "user_id" ,nullable = false,referencedColumnName = "user_id")
    private String userId;

    /*@ManyToOne
    private User user;

    @ManyToOne
    private Tag tag;*/
}
