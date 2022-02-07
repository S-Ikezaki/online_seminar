package com.example.online_seminar.repository;

import com.example.online_seminar.entity.user.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CertificationRepository extends JpaRepository<Certification,Long>{
    Certification findByUserId(String userId);

    boolean existsByUserId (String userId);

    @Query(name =  "Certification.updatePasswordByIdNq" , nativeQuery = true)
    String updatePasswordById(String password , String user_id);

//    @Query("update Certification c set c.password = :pass where c.userId = :userId")
//    @Modifying
//    @Modifying(clearAutomatically = false)
//    int setPass(@Param("user_id") Long user_id, @Param("password") String password);
}
