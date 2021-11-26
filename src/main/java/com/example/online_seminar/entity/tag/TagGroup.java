package com.example.online_seminar.entity.tag;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class TagGroup{
    @Id
    @NotBlank
    private int id;

    @Id
    @NotBlank
    private int tag_id;

    @Id
    @NotBlank
    private String group_id;

   /* @ManyToOne
    private Tag tag;

    @ManyToOne
    private Group group;*/
}