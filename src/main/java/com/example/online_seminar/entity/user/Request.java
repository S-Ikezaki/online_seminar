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
@Table(name = "request_mst")
public class Request implements Serializable {
    @Id
    @Column(name = "request_id" ,nullable = false)
    private long request_id;

    @Id
    @Column(name = "user_id" ,nullable = false)
    private long create_user_id;

    @Column(name = "user_name")
    private String create_user_name;

    @Column(name = "request_content" ,nullable = false)
    private String request_content;

    @Column(name = "request_datetime" ,nullable = false)
    private Date request_datetime;

   /* @ManyToOne
    private User user;

    @OneToMany
    List<TagRequest> tagRequestList;*/
}
