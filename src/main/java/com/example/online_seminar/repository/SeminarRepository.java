package com.example.online_seminar.repository;

import com.example.online_seminar.entity.GroupMst;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeminarRepository extends JpaRepository<GroupMst, Long> {
}
