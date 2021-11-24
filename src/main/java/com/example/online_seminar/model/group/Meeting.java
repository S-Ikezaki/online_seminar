package com.example.online_seminar.model.group;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@Entity
public class Meeting {
    @Id
    @NotBlank
    private String meeting_id;

    private String group_id;
    private String user_name;
    private int status;

    /*@ManyToOne
    private Group group;

    @OneToMany
    List<MeetingMember> meetingMember;

    @OneToMany
    List<MeetingChat> meetingChats;*/
}
