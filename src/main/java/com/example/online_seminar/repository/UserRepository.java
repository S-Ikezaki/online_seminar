package com.example.online_seminar.repository;

import com.example.online_seminar.model.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Certification,Integer> {
}
