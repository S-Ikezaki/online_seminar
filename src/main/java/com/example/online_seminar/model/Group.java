package com.example.online_seminar.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.internal.engine.groups.GroupWithInheritance;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@Entity
public class Group {
    @Id
    @NotBlank
    private String group_id;

    private String group_name;
    private int role;
    private String group_bio;
    private boolean delete_flag;

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
