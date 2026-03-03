package com.edutech.progressive.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.edutech.progressive.entity.Match;


  @Repository
public interface MatchRepository extends JpaRepository<Match, Integer> {

    // Fetch a match by its domain identifier
    Match findByMatchId(int matchId);

    // Filter by status (e.g., "Pending", "Scheduled", "Completed")
    List<Match> findAllByStatus(String status);

    /**
     * Deletes all matches where a team is involved as either firstTeam or secondTeam.
     * This is used when deleting a Team to maintain referential integrity.
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM Match m WHERE m.firstTeam.teamId = :teamId OR m.secondTeam.teamId = :teamId")
    void deleteByTeamId(@Param("teamId") int teamId);
}

