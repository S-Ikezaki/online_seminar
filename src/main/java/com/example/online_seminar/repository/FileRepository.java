package com.example.online_seminar.repository;

import com.example.online_seminar.entity.group.File;
import com.example.online_seminar.entity.group.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface FileRepository extends JpaRepository<File,Long> {

    @Transactional
    @Query
    File findByFileId(int fileId);

    @Transactional
    @Query
    List<File> deleteByFileId(int fileId);

}