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
@Table(name = "tag_mst")
public class Tag{
    @Id
    @Column(name = "tag_id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tagId;

    @Column(name ="tag_name",nullable = false)
    private String tagName;

    @OneToMany
    @JoinColumn(name = "tag_id",referencedColumnName = "tag_id")
    List<TagUser> tagUsers;

    @OneToMany
    @JoinColumn(name = "tag_id",referencedColumnName = "tag_id")
    List<TagRequest> tagRequestList;

    @OneToMany
    @JoinColumn(name = "tag_id",referencedColumnName = "tag_id")
    List<TagGroup> tagGroups;
}
