package com.example.online_seminar.repository;

import com.example.online_seminar.model.Employee;
import com.example.online_seminar.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
