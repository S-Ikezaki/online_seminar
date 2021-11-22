package com.example.online_seminar.repository;

import com.example.online_seminar.model.Meeting;
import com.example.online_seminar.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {
}
