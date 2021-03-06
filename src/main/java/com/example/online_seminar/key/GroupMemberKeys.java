package com.example.online_seminar.key;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;

@Data
public class GroupMemberKeys implements Serializable {
    private int groupId;

    private String userId;

    private String userName;

    private int groupRole;
}
