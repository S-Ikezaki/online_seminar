package com.example.online_seminar.repository;

import com.example.online_seminar.model.Meeting;
import com.example.online_seminar.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
