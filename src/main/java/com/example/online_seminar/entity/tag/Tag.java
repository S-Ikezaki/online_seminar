package com.example.online_seminar.entity.tag;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;


@Entity
@Data
@Table(name = "tag")
public class Tag{
    @Id
    @Column(name = "tag_id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tagId;

    @Column(name ="tagName",nullable = false)
    private String tagName;

    @OneToMany
    List<TagUser> tagUsers;

    @OneToMany
    List<TagRequest> tagRequests;

    @OneToMany
    List<TagGroup> tagGroups;
}
