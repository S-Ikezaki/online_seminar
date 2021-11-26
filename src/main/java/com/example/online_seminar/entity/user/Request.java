package com.example.online_seminar.entity.user;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Data
@Table(name = "request_mst")
public class Request implements Serializable {
    @Id
    @Column(name = "request_id" ,nullable = false)
    private long requestId;

    @Id
    @Column(name = "user_id" ,nullable = false)
    private long createUserId;

    @Column(name = "user_name")
    private String createUserName;

    @Column(name = "request_content" ,nullable = false)
    private String requestContent;

    @Column(name = "request_datetime" ,nullable = false)
    private Date requestDatetime;

   /* @ManyToOne
    private User user;

    @OneToMany
    List<TagRequest> tagRequestList;*/
}
