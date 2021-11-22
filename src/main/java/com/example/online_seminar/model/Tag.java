package com.example.online_seminar.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@Entity
public class Tag {
    @Id
    @NotBlank
    private int tag_id;


    private String tag_name;
    private boolean delete_flag;

    @OneToMany
    List<TagUser> tagUsers;

    @OneToMany
    List<TagRequest> tagRequests;
}
