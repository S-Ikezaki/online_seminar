package com.example.online_seminar.model.user;

import com.example.online_seminar.model.tag.TagRequest;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Request implements Serializable {
    @Id
    @NotBlank
    private String request_id;

    @Id
    @NotBlank
    private String create_user_id;

    private String create_user_name;

    @Id
    @NotBlank
    private String address_user_id;

    private String address_user_name;
    private String request_content;
    private Date request_datetime;

   /* @ManyToOne
    private User user;

    @OneToMany
    List<TagRequest> tagRequestList;*/
}
