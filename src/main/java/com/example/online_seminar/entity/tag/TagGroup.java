package com.example.online_seminar.entity.tag;

import com.example.online_seminar.entity.group.Group;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Data
@Table(name = "tag_group_mst")
public class TagGroup{
    @Id
    @Column(name = "tag_id", nullable = false)
    private int tagId;

    @Id
    @Column(name = "group_id", nullable = false)
    private String groupId;

    @ManyToOne
    @JoinColumn(name = "tag_id", nullable = false,referencedColumnName = "tag_id")
    private Tag tag;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false, referencedColumnName = "group_id")
    private Group group;
}
