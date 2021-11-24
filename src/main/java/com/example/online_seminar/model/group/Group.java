package com.example.online_seminar.model.group;

import com.example.online_seminar.model.tag.TagGroup;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Entity
public class Group {
    @Id
    @NotBlank
    private String groupId;

    private String groupName;

    @NotNull
    private int role;

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
