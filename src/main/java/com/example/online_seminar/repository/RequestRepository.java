package com.example.online_seminar.repository;

import com.example.online_seminar.entity.user.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request,Long> {
}