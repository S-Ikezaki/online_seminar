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
@Table(name = "tag_request")
public class TagRequest{
    @Id
    @JoinColumn(name = "tag_id",referencedColumnName = "tag_id")
    private int tagId;

    @Id
    @JoinColumn(name = "request_id" ,nullable = false,referencedColumnName = "request_id")
    private long requestId;

    @ManyToOne
    private Request request;

    @ManyToOne
    private Tag tag;
}
