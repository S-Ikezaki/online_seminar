package com.example.online_seminar.entity.group;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Currency;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "meeting_mst")
public class Meeting{
    @Id
    @Column(name = "group_id",nullable = false)
    private String groupId;

    @Column(name = "user_name")
    private String userName;

    @ManyToOne
    @JoinColumn(name = "group_id",referencedColumnName = "group_id",insertable=false, updatable=false)
    private Group group;

}
