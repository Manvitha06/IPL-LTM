package com.edutech.progressive.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.edutech.progressive.entity.Cricketer;

@Repository
public interface CricketerRepository extends JpaRepository<Cricketer, Integer> {

    Cricketer findByCricketerId(int cricketerId);
        List<Cricketer> findByTeamId(int teamId);

   // List<Cricketer> findByTeam_TeamId(int teamId);
   
long countByTeam_TeamId(int teamId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Cricketer c WHERE c.team.teamId = :teamId")
    void deleteByTeamId(int teamId);


}

