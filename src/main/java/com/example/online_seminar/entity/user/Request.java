package com.example.online_seminar.entity.user;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;

@Entity
@Data
@Table(name = "request_mst")
public class Request implements Serializable {
    @Id
    @Column(name = "request_id" ,nullable = false)
    private int requestId;

    @Column(name = "user_id" ,nullable = false)
    private String requestUserId;

    @Column(name = "user_name")
    private String requestUserName;

    @Column(name = "request_content" ,nullable = false)
    private String requestContent;

    @Column(name = "request_datetime" ,nullable = false)
    private String requestDatetime;

//    @ManyToOne
//    @JoinColumn(name = "user_id",referencedColumnName = "user_id",insertable = false, updatable=false)
//    private User user;

}
