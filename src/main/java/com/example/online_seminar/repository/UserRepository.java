package com.example.online_seminar.repository;

import com.example.online_seminar.entity.group.Group;
import com.example.online_seminar.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    //何らかのタグを持つユーザーのタグ名とユーザー名を一覧表示
    @Query(name = "User.findByTagNqVariable",nativeQuery = true)
    List<User> findByTagNq(String Tag);

    @Query(name = "User.findByUserRoleVariable")
    List<User> findByUserRole(String userRole);

    @Query(name = "User.findStudentByRoleVariable")
    List<User> findStudentByRole(String userRole);

    @Query(name = "User.findStudentByTagNqVariable",nativeQuery = true)
    List<User> findStudentByTagNq(String Tag);

    @Query(name = "User.findStudentAllInfoByTagNqVariable",nativeQuery = true)
    List<User> findStudentAllInfoByTagNq(String Tag);

    @Query(name = "User.findStudentInGroupByRoleNqVariable",nativeQuery = true)
    List<User> findStudentInGroupByRoleNq(String userRole);

    @Query(name = "User.findTeacherByRoleVariable")
    List<User> findTeacherByRole(String userRole);

    @Query(name = "User.findTeacherByTagNqVariable",nativeQuery = true)
    List<User> findTeacherByTagNq(String Tag);

    @Query(name = "User.findTeacherAllInfoByTagNqVariable",nativeQuery = true)
    List<User> findTeacherAllInfoByTagNq(String Tag);

    @Query(name = "User.findTeacherInGroupByRoleNqVariable",nativeQuery = true)
    List<User> findTeacherInGroupByRoleNq(String userRole);

    @Query(name = "User.findEmployeeByRoleVariable")
    List<User> findEmployeeByRole(String userRole);



}
