package com.example.online_seminar.repository;

import com.example.online_seminar.entity.group.File;
import com.example.online_seminar.entity.group.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<File,Long> {
    File findByFileId(int fileId);
}
