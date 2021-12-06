package com.example.online_seminar.entity.tag;

import com.example.online_seminar.entity.user.Request;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Data
@Table(name = "tag_request_mst")
public class TagRequest{
    @Id
    @Column(name = "tag_id")
    private int tagId;

    @Id
    @Column(name = "request_id")
    private long requestId;

    @ManyToOne
    @JoinColumn(name = "request_id", referencedColumnName = "request_id")
    private Request request;

    @ManyToOne
    @JoinColumn(name = "tag_id", referencedColumnName = "tag_id")
    private Tag tag;
}
