package com.example.online_seminar.entity.tag;

import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@Table(name = "tag_mst")
@IdClass(TagUserKey.class)
public class Tag{
    @Id
    @Column(name = "tag_id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tagId;

    @Column(name ="tag_name",nullable = false)
    private String tagName;

    @OneToMany(mappedBy = "tag")
    List<TagUser> tagUsers;

    @OneToMany(mappedBy = "tag")
    List<TagRequest> tagRequestList;

    @OneToMany(mappedBy = "tag")
    List<TagGroup> tagGroups;
}
