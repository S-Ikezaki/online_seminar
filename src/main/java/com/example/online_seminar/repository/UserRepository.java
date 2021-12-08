package com.example.online_seminar.repository;

import com.example.online_seminar.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    //何らかのタグを持つユーザーのタグ名とユーザー名を一覧表示
    @Query(name = "User.findByTagNq",nativeQuery = true)
    List<User> findByTagNq(String tagId);

    @Query(name = "User.findByUserRole")
    List<User> findByUserRole(String userRole);

    @Query(name = "User.findStudentByRoleNq", nativeQuery = true)
    List<User> findStudentByRole(String userRole);

    @Query(name = "User.findStudentByTagNq",nativeQuery = true)
    List<User> findStudentByTagNq(String tagId);

    @Query(name = "User.findStudentAllInfoByTagNq",nativeQuery = true)
    List<User> findStudentAllInfoByTagNq(String tagId);

    @Query(name = "User.findStudentInGroupByRoleNq",nativeQuery = true)
    List<User> findStudentInGroupByRoleNq(String userRole);

    @Query(name = "User.findTeacherByRoleNQ", nativeQuery = true)
    List<User> findTeacherByRole(String userRole);

    @Query(name = "User.findTeacherByTagNq",nativeQuery = true)
    List<User> findTeacherByTagNq(String tagId);

    @Query(name = "User.findTeacherAllInfoByTagNq",nativeQuery = true)
    List<User> findTeacherAllInfoByTagNq(String tagId);

    @Query(name = "User.findTeacherInGroupByRoleNq",nativeQuery = true)
    List<User> findTeacherInGroupByRoleNq(String userRole);

    @Query(name = "User.findEmployeeByRole")
    List<User> findEmployeeByRole(String userRole);



}
