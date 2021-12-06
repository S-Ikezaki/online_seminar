package com.example.online_seminar.repository;

import com.example.online_seminar.entity.group.Group;
import com.example.online_seminar.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    //何らかのタグを持つユーザーのタグ名とユーザー名を一覧表示
    @Query(name = "User.findByTagNq",nativeQuery = true)
    List<User> findByTagNq(String Tag);

    @Query(name = "User.findByUserRole")
    List<User> findByUserRole(String userRole);

    @Query(name = "User.findStudentByRole")
    List<User> findStudentByRole(String userRole);

    @Query(name = "User.findStudentByTagNq",nativeQuery = true)
    List<User> findStudentByTagNq(String Tag);

    @Query(name = "User.findStudentAllInfoByTagNq",nativeQuery = true)
    List<User> findStudentAllInfoByTagNq(String Tag);

    @Query(name = "User.findStudentInGroupByRoleNq",nativeQuery = true)
    List<User> findStudentInGroupByRoleNq(String userRole);

    @Query(name = "User.findTeacherByRole")
    List<User> findTeacherByRole(String userRole);

    @Query(name = "User.findTeacherByTagNq",nativeQuery = true)
    List<User> findTeacherByTagNq(String Tag);

    @Query(name = "User.findTeacherAllInfoByTagNq",nativeQuery = true)
    List<User> findTeacherAllInfoByTagNq(String Tag);

    @Query(name = "User.findTeacherInGroupByRoleNq",nativeQuery = true)
    List<User> findTeacherInGroupByRoleNq(String userRole);

    @Query(name = "User.findEmployeeByRole")
    List<User> findEmployeeByRole(String userRole);



}
