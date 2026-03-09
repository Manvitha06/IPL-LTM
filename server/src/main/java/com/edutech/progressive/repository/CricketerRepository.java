package com.edutech.progressive.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.edutech.progressive.entity.Cricketer;

@Repository
public interface CricketerRepository extends JpaRepository<Cricketer, Integer> {


    Cricketer findByCricketerId(int cricketerId);

    // ✅ Use the association path, not a raw teamId column method
    List<Cricketer> findByTeamId(int teamId);

    long countByTeam_TeamId(int teamId);

   
 @Modifying
    @Transactional
    @Query(value = "DELETE FROM cricketer WHERE team_id = :teamId", nativeQuery = true)
    void deleteByTeamId(@Param("teamId") int teamId);
}

