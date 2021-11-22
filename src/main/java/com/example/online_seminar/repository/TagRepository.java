package com.example.online_seminar.repository;

import com.example.online_seminar.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag,Long> {
}
