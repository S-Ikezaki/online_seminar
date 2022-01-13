package com.example.online_seminar.entity.group;

import com.example.online_seminar.entity.tag.TagGroup;
import com.example.online_seminar.entity.user.Participation;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "group_mst")
public class Group implements Serializable {
    @Id
    @Column(name = "group_id",nullable = false)
    private int groupId;

    @Column(name = "group_name",nullable = false)
    private String groupName;

    @Column(name = "role",nullable = false)
    private int groupRole;

    @Column(name = "group_bio")
    private String groupBio;

    @OneToMany(mappedBy = "group")
    List<TagGroup> tagGroups;

    @OneToMany(mappedBy = "group")
    List<File> files;

    @OneToMany(mappedBy = "group")
    List<GroupMember> groupMembers;

    @OneToMany(mappedBy = "group")
    List<GroupMessage> groupMessages;

    @OneToOne
    @JoinColumn(name = "group_id",referencedColumnName = "group_id")
    private Meeting meetings;

//    @OneToMany
//    @JoinColumn(name = "group_id", referencedColumnName = "group_id")
//    List<Participation> participation;

}
