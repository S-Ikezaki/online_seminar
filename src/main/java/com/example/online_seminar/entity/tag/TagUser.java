package com.example.online_seminar.entity.tag;


import com.example.online_seminar.entity.user.User;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "tag_user_mst")
public class TagUser implements Serializable{
    @Id
    @Column(name = "tag_id",nullable = false)
    private int tagId;

    @Id
    @Column(name = "user_id",nullable = false)
    private String userId;

    @ManyToOne
    @JoinColumn(name = "user_id" ,referencedColumnName = "user_id",insertable = false, updatable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "tag_id",referencedColumnName = "tag_id",insertable = false, updatable=false)
    private Tag tag;
}
