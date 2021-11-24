package com.example.online_seminar.model.tag;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
public class Tag implements Serializable{
    @Id
    @NotBlank
    private int tag_id;

    private String tag_name;

    /*@OneToMany
    List<TagUser> tagUsers;

    @OneToMany
    List<TagRequest> tagRequests;*/
}
