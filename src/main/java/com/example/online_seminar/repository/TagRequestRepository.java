package com.example.online_seminar.repository;

import com.example.online_seminar.model.tag.TagRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRequestRepository extends JpaRepository<TagRequest,Long> {
}