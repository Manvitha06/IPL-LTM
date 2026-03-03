package com.edutech.progressive.repository;
import com.edutech.progressive.entity.TicketBooking;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import javax.transaction.Transactional;
@Repository
public interface TicketBookingRepository extends JpaRepository<TicketBooking, Integer> {
    List<TicketBooking> findByEmail(String email);
    @Modifying
    @Transactional
    @Query("DELETE FROM TicketBooking tb WHERE tb.match.firstTeam.teamId = :teamId OR tb.match.secondTeam.teamId = :teamId")
    void deleteByTeamId(@Param("teamId") int teamId);
    @Modifying
    @Transactional
    @Query("DELETE FROM TicketBooking tb WHERE tb.match.matchId = :matchId")
    void deleteByMatchId(@Param("matchId") int matchId);
}


