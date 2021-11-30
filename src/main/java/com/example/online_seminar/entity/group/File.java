package com.example.online_seminar.entity.group;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Data
@Table(name = "file_mst")
public class File {
    @Id
    @Column(name = "file_id",nullable = false)
    private String fileId;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_pass",nullable = false)
    private String filePass;

    @JoinColumn(name = "user_id",nullable = false,referencedColumnName = "user_id")
    private String userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "create_datetime")
    private Date createDatetime;

    @JoinColumn(name = "group_id",nullable = false,referencedColumnName = "group_id")
    private String groupId;

    /*@ManyToOne
    private Group group;*/
}
