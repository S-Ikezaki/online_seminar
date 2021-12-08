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

    @Column(name = "group_bio")
    private String groupBio;

    @OneToMany(mappedBy = "group")
    @JoinColumn(name="group_id",referencedColumnName = "group_id",insertable = false, updatable=false)
    List<TagGroup> tagGroups;

    @OneToMany(mappedBy = "group")
    @JoinColumn(name="group_id",referencedColumnName = "group_id",insertable = false, updatable=false)
    List<File> files;

    @OneToMany(mappedBy = "group")
    @JoinColumn(name="group_id",referencedColumnName = "group_id",insertable = false, updatable=false)
    List<GroupMember> groupMembers;

    @OneToMany(mappedBy = "group")
    @JoinColumn(name="group_id",referencedColumnName = "group_id",insertable = false, updatable=false)
    List<GroupMessage> groupMessages;

    @OneToMany(mappedBy = "group")
    @JoinColumn(name="group_id",referencedColumnName = "group_id",insertable = false, updatable=false)
    List<Meeting> meetings;

}
