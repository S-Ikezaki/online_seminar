package com.example.online_seminar.entity.group;

import lombok.CustomLog;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Currency;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
@Table(name = "group_message")
public class GroupMessage {
    @Id
    @Column(name="group_message_id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupMessageId;

    @Id
    @JoinColumn(name="group_id",referencedColumnName = "group_id")
    private String groupId;

    @Column(name="create_datetime")
    private Date createDatetime;

    @Column(name="message_contents")
    private String messageContents;


    @ManyToOne
    private Group group;
}
