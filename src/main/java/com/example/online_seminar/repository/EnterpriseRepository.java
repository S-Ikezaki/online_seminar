package com.example.online_seminar.repository;

import com.example.online_seminar.model.user.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnterpriseRepository extends JpaRepository<Enterprise,Long> {
}
