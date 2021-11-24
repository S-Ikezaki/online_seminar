package com.example.online_seminar.repository;

import com.example.online_seminar.model.user.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {
}
