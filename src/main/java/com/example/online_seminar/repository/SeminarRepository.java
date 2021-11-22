package com.example.online_seminar.repository;

import com.example.online_seminar.model.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeminarRepository extends JpaRepository<Certification,Integer>{
}