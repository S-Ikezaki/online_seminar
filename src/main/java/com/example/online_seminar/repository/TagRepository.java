package com.example.online_seminar.repository;

import com.example.online_seminar.entity.group.Group;
import com.example.online_seminar.entity.tag.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag,Long> {

    @Query(name = "Tag.findByGroupNq",nativeQuery = true)
    List<Tag> findByGroup(String groupId);

    List<Tag> findByTagName(String tag);
}
