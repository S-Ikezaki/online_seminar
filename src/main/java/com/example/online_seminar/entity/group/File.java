package com.example.online_seminar.entity.group;

import lombok.Data;

import javax.persistence.*;
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

    @Column(name = "user_name")
    private String userName;

    @Column(name = "create_datetime")
    private Date createDatetime;

    @Column(name = "group_id",nullable = false)
    private String groupId;

    @ManyToOne
<<<<<<< HEAD
    @JoinColumn(name = "group_id",referencedColumnName = "group_id",insertable = false, updatable=false)
=======
    @JoinColumn(name = "group_id",referencedColumnName = "group_id",insertable=false, updatable=false)
>>>>>>> 8af5cf9e324216bd399fd821adcfb762e7ee0d4a
    private Group group;

}
