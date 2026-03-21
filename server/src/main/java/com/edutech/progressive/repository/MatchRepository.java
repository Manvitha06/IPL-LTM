package com.edutech.progressive.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.edutech.progressive.entity.Match;
@Repository
public interface MatchRepository extends JpaRepository<Match,Integer> {
    Match findByMatchId(int matchId);
     @EntityGraph(attributePaths = {"firstTeam", "secondTeam"})
    List<Match> findAll();
    @EntityGraph(attributePaths = {"firstTeam", "secondTeam"})
    List<Match> findAllByStatus(String status);
   @Transactional
@Modifying
@Query("DELETE FROM Match m WHERE m.firstTeam.teamId IN (SELECT t.teamId FROM Team t WHERE t.teamId = :teamId)")
void deleteByFirstTeamId(@Param("teamId") int teamId);
@Transactional
@Modifying
@Query("DELETE FROM Match m WHERE m.secondTeam.teamId IN (SELECT t.teamId FROM Team t WHERE t.teamId = :teamId)")
void deleteBySecondTeamId(@Param("teamId") int teamId);
}