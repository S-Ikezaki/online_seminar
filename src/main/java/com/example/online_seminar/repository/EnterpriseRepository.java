package com.example.online_seminar.repository;

import com.example.online_seminar.model.Enterprise;
import com.example.online_seminar.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnterpriseRepository extends JpaRepository<Enterprise,Long> {
}
