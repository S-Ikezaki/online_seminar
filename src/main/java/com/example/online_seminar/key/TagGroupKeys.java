package com.example.online_seminar.key;

import com.sun.source.doctree.SerialDataTree;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public class TagGroupKeys implements Serializable {

    private int tagId;

    private int groupId;

}
