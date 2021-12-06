package com.example.online_seminar.entity.group;

import com.example.online_seminar.entity.tag.TagGroup;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "group_mst")
public class Group implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id",nullable = false)
    private String groupId;

    @Column(name = "group_name",nullable = false)
    private String groupName;

    @Column(name = "role",nullable = false)
    private int groupRole;

    private String groupBio;

    @OneToMany
    List<TagGroup> tagGroups;

    @OneToMany
    List<File> files;

    @OneToMany
    List<GroupMember> groupMembers;

    @OneToMany
    List<GroupMessage> groupMessages;

    @OneToMany
    List<Meeting> meetings;

}
