// src/main/java/com/edutech/progressive/repository/MatchRepository.java
package com.edutech.progressive.repository;

import com.edutech.progressive.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Integer> {

    Match findByMatchId(int matchId);

    List<Match> findAllByStatus(String status);

    // ✅ Native delete avoids HQL bulk delete with association cross-joins
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM matches WHERE first_team_id = :teamId OR second_team_id = :teamId",
           nativeQuery = true)
    void deleteByTeamId(@Param("teamId") int teamId);
}

