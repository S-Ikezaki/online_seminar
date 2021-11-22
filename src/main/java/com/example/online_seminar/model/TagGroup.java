package com.example.online_seminar.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
public class TagGroup {
    @Id
    @NotBlank
    private int id;

    @Id
    @NotBlank
    private int tag_id;

    @Id
    @NotBlank
    private String group_id;
    private boolean delete_flag;

    @ManyToOne
    private Tag tag;

    @ManyToOne
    private Group group;
}
