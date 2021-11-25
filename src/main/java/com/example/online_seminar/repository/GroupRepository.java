package com.example.online_seminar.repository;

import com.example.online_seminar.entity.group.Group;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group,Integer>{
}
