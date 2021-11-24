package com.example.online_seminar.repository;

import com.example.online_seminar.model.group.Group;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group,Long> {
}
