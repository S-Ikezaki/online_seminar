package com.example.online_seminar.entity.user;

import com.example.online_seminar.entity.tag.TagRequest;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Data
@Table(name = "request_mst")
public class Request implements Serializable {
    @Id
    @Column(name = "request_id" ,nullable = false)
    private long requestId;

    @JoinColumn(name = "user_id" ,nullable = false,referencedColumnName = "user_id")
    private long requestUserId;

    @JoinColumn(name = "user_id",nullable = false,referencedColumnName = "teacher_id")
    private long teacherId;

    @JoinColumn(name = "group_id",nullable = false,referencedColumnName = "group_id")
    private long groupId;

    @Column(name = "user_name")
    private String requestUserName;

    @Column(name = "request_content" ,nullable = false)
    private String requestContent;

    @Column(name = "request_datetime" ,nullable = false)
    private Date requestDatetime;

    @ManyToOne
    private User user;

    @OneToMany
    List<TagRequest> tagRequestList;
}
