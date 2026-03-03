package com.edutech.progressive.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.edutech.progressive.entity.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    // Total count of votes for a category
    Long countByCategory(String category);

    // Delete all votes associated to a Team
    @Modifying
    @Transactional
    @Query("DELETE FROM Vote v WHERE v.team.teamId = :teamId")
    void deleteByTeamId(@Param("teamId") int teamId);

    // Delete all votes associated to a Cricketer
    @Modifying
    @Transactional
    @Query("DELETE FROM Vote v WHERE v.cricketer.cricketerId = :cricketerId")
    void deleteByCricketerId(@Param("cricketerId") int cricketerId);
}



