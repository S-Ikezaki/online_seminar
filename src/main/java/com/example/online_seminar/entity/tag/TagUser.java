package com.example.online_seminar.entity.tag;

import com.example.online_seminar.entity.user.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Data
@Table(name = "tag_user_mst")
public class TagUser{
    @Id
    @JoinColumn(name = "tag_id",referencedColumnName = "tag_id")
    private int tagId;

    @Id
    @Column(name = "user_id",nullable = false)
    private String userId;

    @ManyToOne
    @JoinColumn(name = "user_id" ,referencedColumnName = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "tag_id",referencedColumnName = "tag_id")
    private Tag tag;
}
