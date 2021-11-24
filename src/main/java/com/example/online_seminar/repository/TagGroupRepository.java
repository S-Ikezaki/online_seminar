package com.example.online_seminar.repository;

import com.example.online_seminar.model.tag.TagGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagGroupRepository extends JpaRepository<TagGroup,Long> {
}
