package com.edutech.progressive.repository;

import com.edutech.progressive.entity.TicketBooking;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TicketBookingRepository extends JpaRepository<TicketBooking, Integer> {

    List<TicketBooking> findByEmail(String email);

    // ✅ native: delete bookings for matches involving teamId
    @Modifying
    @Transactional
    @Query(value = "DELETE tb FROM ticket_booking tb " +
                   "JOIN matches m ON tb.match_id = m.match_id " +
                   "WHERE m.first_team_id = :teamId OR m.second_team_id = :teamId",
           nativeQuery = true)
    void deleteByTeamId(@Param("teamId") int teamId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM ticket_booking WHERE match_id = :matchId", nativeQuery = true)
    void deleteByMatchId(@Param("matchId") int matchId);
}