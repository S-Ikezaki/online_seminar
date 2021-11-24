package com.example.online_seminar.repository;

<<<<<<< HEAD
import com.example.online_seminar.model.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Certification,Integer> {
=======
import com.example.online_seminar.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
>>>>>>> e0e7db5fa17b732b65ebd7219b17ebded4b549fb
}
