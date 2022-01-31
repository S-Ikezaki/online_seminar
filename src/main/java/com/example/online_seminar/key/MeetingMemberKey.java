package com.example.online_seminar.key;

import lombok.Data;

import java.io.Serializable;

@Data
public class MeetingMemberKey implements Serializable {

    private int groupId;

    private String userName;

}
