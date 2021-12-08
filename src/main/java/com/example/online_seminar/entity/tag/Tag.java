package com.example.online_seminar.entity.tag;

import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@Table(name = "tag_mst")
public class Tag{
    @Id
    @Column(name = "tag_id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tagId;

    @Column(name ="tag_name",nullable = false)
    private String tagName;

    @OneToMany(mappedBy = "tag")
    @JoinColumn(name = "tag_id",referencedColumnName = "tag_id",insertable = false, updatable=false)
    List<TagUser> tagUsers;

    @OneToMany(mappedBy = "tag")
    @JoinColumn(name = "tag_id",referencedColumnName = "tag_id",insertable = false, updatable=false)
    List<TagRequest> tagRequestList;

    @OneToMany(mappedBy = "tag")
    @JoinColumn(name = "tag_id",referencedColumnName = "tag_id",insertable = false, updatable=false)
    List<TagGroup> tagGroups;
}
