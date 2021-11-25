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
@Table(name = "direct_message_mst")
public class DirectMessage implements Serializable {
    @Id
    @Column(name = "direct_message_id" ,nullable = false)
    private long directMessageId;

    @Id
    @Column(name = "create_user_id" ,nullable = false)
    private long createUserId;

    @Column(name = "create_user_name" ,nullable = false)
    private String createUserName;

    @Id
    @Column(name ="address_user_id" ,nullable = false)
    private long addressUserId;

    @Column(name = "address_user_name" ,nullable = false)
    private String addressUserName;

    @Column(name = "direct_message_content" ,nullable = false)
    private String directMessageContent;

    @Column(name = "create_datetime" ,nullable = false)
    private Date createDatetime;

    /*@ManyToOne
    private User user;*/
}
