package com.example.online_seminar.repository;

import com.example.online_seminar.model.user.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School,Long> {
}
