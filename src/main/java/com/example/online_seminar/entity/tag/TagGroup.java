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
@Table(name = "tag_group")
public class TagGroup{
    @Id
    @JoinColumn(name = "tag_id", nullable = false,referencedColumnName = "tag_id")
    private int tagId;

    @Id
    @JoinColumn(name = "group_id", nullable = false, referencedColumnName = "group_id")
    private String groupId;

    @ManyToOne
    private Tag tag;

    @ManyToOne
    private Group group;
}
