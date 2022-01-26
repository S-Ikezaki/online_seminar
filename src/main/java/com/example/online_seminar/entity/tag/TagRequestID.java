package com.example.online_seminar.entity.tag;


import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;

@Data
public class TagRequestID implements Serializable {

    private int tagId;
    private int requestId;
}
