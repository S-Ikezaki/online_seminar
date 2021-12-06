package com.example.online_seminar.repository;

import com.example.online_seminar.entity.user.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificationRepository extends JpaRepository<Certification,Long>{
    Certification findByUserId(String userId);

    boolean existsByUserId (String userId);
}
