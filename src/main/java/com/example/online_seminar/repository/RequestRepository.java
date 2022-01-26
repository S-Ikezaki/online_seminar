package com.example.online_seminar.repository;

import com.example.online_seminar.entity.user.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface RequestRepository extends JpaRepository<Request,Long> {

    @Query(name = "Request.findBy", nativeQuery = true)
    List<Request> findAllSelect();

    Request findByRequestDatetime(String date);
}
