package com.example.online_seminar.repository;

import com.example.online_seminar.entity.user.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ParticipationRepository extends JpaRepository<Participation, Integer> {

    List<Participation> findByGroupId(int groupId);

    void deleteByParticipationId(int participationId);

}
