package com.example.online_seminar.model.group;

import com.example.online_seminar.model.group.Group;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Entity
@Getter
@Setter
public class File {
    @Id
    @NotBlank
    private String file_id;

    private String file_name;
    private String file_pass;
    private String user_id;
    private String user_name;
    private Date create_datetime;
    private String group_id;
    private boolean delete_flag;

    @ManyToOne
    private Group group;
}
