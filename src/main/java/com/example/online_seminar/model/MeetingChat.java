package com.example.online_seminar.model;

import lombok.Data;

@Data
public class MeetingChat {
    private String meeting_chat_id;
    private String meeting_chat_contents;
    private String meeting_id;
    private String user_id;
    private String user_name;
    private String meeting_chat_datetime;
    private boolean delete_flag;
}
