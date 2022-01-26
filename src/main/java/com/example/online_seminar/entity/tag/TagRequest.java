package com.example.online_seminar.entity.tag;

import com.example.online_seminar.entity.user.Request;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "tag_request_mst")
@IdClass(TagRequestID.class)
public class TagRequest implements Serializable{
    @Id
    @Column(name = "tag_id")
    private int tagId;

    @Id
    @Column(name = "request_id")
    private int requestId;

    @ManyToOne
    @JoinColumn(name = "request_id", referencedColumnName = "request_id",insertable = false, updatable=false)
    private Request request;

    @ManyToOne
    @JoinColumn(name = "tag_id", referencedColumnName = "tag_id",insertable = false, updatable=false)
    private Tag tag;
}
