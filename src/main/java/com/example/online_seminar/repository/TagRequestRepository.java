package com.example.online_seminar.repository;

import com.example.online_seminar.model.Meeting;
import com.example.online_seminar.model.TagRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRequestRepository extends JpaRepository<TagRequest,Long> {
}
