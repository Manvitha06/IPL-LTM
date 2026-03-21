package com.edutech.progressive.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.edutech.progressive.entity.TicketBooking;
@Repository
public interface TicketBookingRepository extends JpaRepository<TicketBooking,Integer > {
    List<TicketBooking> findByEmail(String email);
    @Transactional
    @Modifying
    @Query("DELETE FROM TicketBooking t " +
       "WHERE t.match.id IN (" +
       "  SELECT m.id FROM Match m " +
       "  WHERE m.firstTeam.teamId = :teamId OR m.secondTeam.teamId = :teamId" +
       ")")
    void deleteByTeamId(@Param("teamId") int teamId);
    
    @Transactional
    @Modifying
    @Query("delete from TicketBooking t where t.match.matchId=:matchId")
    void deleteByMatchId(@Param("matchId")int matchId);
}