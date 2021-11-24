package com.example.online_seminar.model.tag;

import com.example.online_seminar.model.tag.Tag;
import com.example.online_seminar.model.user.Request;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
public class TagRequest {
    @Id
    @NotBlank
    private int id;

    @Id
    @NotBlank
    private int tag_id;

    @ManyToOne
    private Request request;

    @ManyToOne
    private Tag tag;
}
