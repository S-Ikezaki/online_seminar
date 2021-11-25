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
public class Tag{
    @Id
    @NotBlank
    private int tag_id;

    private String tag_name;

    /*@OneToMany
    List<TagUser> tagUsers;

    @OneToMany
    List<TagRequest> tagRequests;*/
}
