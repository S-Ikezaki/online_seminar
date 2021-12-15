package com.example.online_seminar.repository;

import com.example.online_seminar.entity.tag.TagRequestKey;
import com.example.online_seminar.entity.tag.TagUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagUserRepository extends JpaRepository<TagUser, TagRequestKey> {
}
