package com.example.online_seminar.repository;

import com.example.online_seminar.model.File;
import com.example.online_seminar.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File,Long> {
}
