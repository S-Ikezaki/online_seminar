package com.example.online_seminar.entity.tag;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class TagRequestKey implements Serializable {
    private int tagId;
    private long requestId;
}
