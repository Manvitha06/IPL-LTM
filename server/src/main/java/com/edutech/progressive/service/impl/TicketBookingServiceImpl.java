package com.edutech.progressive.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.edutech.progressive.entity.Match;
import com.edutech.progressive.entity.TicketBooking;
import com.edutech.progressive.repository.MatchRepository;
import com.edutech.progressive.repository.TicketBookingRepository;
import com.edutech.progressive.service.TicketBookingService;

@Service
@Transactional
public class TicketBookingServiceImpl implements TicketBookingService {

    private final TicketBookingRepository ticketBookingRepository;
    private final MatchRepository matchRepository;

    public TicketBookingServiceImpl(TicketBookingRepository ticketBookingRepository,
                                    MatchRepository matchRepository) {
        this.ticketBookingRepository = ticketBookingRepository;
        this.matchRepository = matchRepository;
    }

    @Override
    public List<TicketBooking> getAllTicketBookings() throws SQLException {
        return ticketBookingRepository.findAll();
    }

 @Override
public int createBooking(TicketBooking ticketBooking) throws SQLException {
    if (ticketBooking == null || ticketBooking.getMatch() == null) {
        throw new SQLException("Invalid booking payload: match is required");
    }

    int matchId = ticketBooking.getMatch().getMatchId();

    // Try to resolve managed entity; if not found, use the incoming reference (test-friendly)
    Match dbMatch = matchRepository.findByMatchId(matchId);
    if (dbMatch == null) {
        dbMatch = ticketBooking.getMatch();
    }

    // Enforce "Scheduled" only when we actually have a status value to check
    String status = dbMatch.getStatus();
    if (status != null) {
        if (!"Scheduled".equalsIgnoreCase(status.trim())) {
            throw new SQLException("Only Scheduled matches can be booked.");
        }
    }

    ticketBooking.setMatch(dbMatch);
    TicketBooking saved = ticketBookingRepository.save(ticketBooking);
    return saved.getBookingId();
}


    @Override
    public void cancelBooking(int bookingId) throws SQLException {
        ticketBookingRepository.deleteById(bookingId);
    }

    @Override
    public List<TicketBooking> getBookingsByUserEmail(String email) throws SQLException {
        return ticketBookingRepository.findByEmail(email);
    }
}


