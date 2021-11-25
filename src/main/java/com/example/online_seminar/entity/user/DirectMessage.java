package com.example.online_seminar.entity.user;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@Entity
@Data
@Table(name = "direct_message_mst")
public class DirectMessage implements Serializable {
    @Id
    @Column(name = "direct_message_id" ,nullable = false)
    private long direct_message_id;

    @Id
    @Column(name = "create_user_id" ,nullable = false)
    private long create_user_id;

    @Column(name = "create_user_name" ,nullable = false)
    private String create_user_name;

    @Id
    @Column(name ="address_user_id" ,nullable = false)
    private long address_user_id;

    @Column(name = "address_user_name" ,nullable = false)
    private String address_user_name;

    @Column(name = "direct_message_content" ,nullable = false)
    private String direct_message_content;

    @Column(name = "create_datetime" ,nullable = false)
    private Date create_datetime;

    /*@ManyToOne
    private User user;*/
}
