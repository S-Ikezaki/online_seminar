package com.example.online_seminar.model.group;

import com.example.online_seminar.model.group.Meeting;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
public class MeetingChat {
    @Id
    @NotBlank
    private String meeting_chat_id;

    private String meeting_chat_contents;
    private String meeting_id;
    private String user_id;
    private String user_name;
    private String meeting_chat_datetime;
    private boolean delete_flag;

    @ManyToOne
    private Meeting meeting;
}
