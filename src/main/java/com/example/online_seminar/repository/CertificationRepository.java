package com.example.online_seminar.repository;

import com.example.online_seminar.model.user.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificationRepository extends JpaRepository<Certification,Long> {
}
