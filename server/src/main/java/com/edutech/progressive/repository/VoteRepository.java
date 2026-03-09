package com.edutech.progressive.repository;

import com.edutech.progressive.entity.Vote;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    Long countByCategory(String category);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM vote WHERE team_id = :teamId", nativeQuery = true)
    void deleteByTeamId(@Param("teamId") int teamId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM vote WHERE cricketer_id = :cricketerId", nativeQuery = true)
    void deleteByCricketerId(@Param("cricketerId") int cricketerId);
}