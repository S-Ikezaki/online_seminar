package com.example.online_seminar.model.tag;

import com.example.online_seminar.model.group.Group;
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
public class TagGroup implements Serializable{
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
