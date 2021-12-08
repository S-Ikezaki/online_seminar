package com.example.online_seminar.entity.tag;

import com.example.online_seminar.entity.group.Group;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "tag_group_mst")
public class TagGroup implements Serializable{
    @Id
    @Column(name = "tag_id", nullable = false)
    private int tagId;

    @Id
    @Column(name = "group_id", nullable = false)
    private String groupId;

    @ManyToOne
    @JoinColumn(name = "tag_id", nullable = false,referencedColumnName = "tag_id",insertable = false, updatable=false)
    private Tag tag;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false, referencedColumnName = "group_id",insertable = false, updatable=false)
    private Group group;
}
