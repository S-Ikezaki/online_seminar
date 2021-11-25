package com.example.online_seminar.repository;

import com.example.online_seminar.entity.group.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File,Long> {
}
