package com.edutech.progressive.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.progressive.entity.Match;
import com.edutech.progressive.exception.NoMatchesFoundException;
import com.edutech.progressive.repository.MatchRepository;
import com.edutech.progressive.repository.TicketBookingRepository;
import com.edutech.progressive.service.MatchService;

@Service

public class MatchServiceImplJpa implements MatchService {
private MatchRepository matchRepository;
@Autowired
    private TicketBookingRepository ticketBookingRepository; // NEW
    @Autowired
    public MatchServiceImplJpa(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
     
    }



    @Override
    public List<Match> getAllMatches() throws SQLException {
        return matchRepository.findAll();
    }

    @Override
    public Match getMatchById(int matchId) throws SQLException {
        return matchRepository.findByMatchId(matchId);
    }

    @Override
    public Integer addMatch(Match match) throws SQLException {
        Match saved = matchRepository.save(match);
        return saved.getMatchId();
    }

    @Override
    public void updateMatch(Match match) throws SQLException {
        matchRepository.save(match);
    }

    @Override

public void deleteMatch(int matchId) throws SQLException {
        // Delete related bookings first
        if (ticketBookingRepository != null) {
            ticketBookingRepository.deleteByMatchId(matchId);
        }
        matchRepository.deleteById(matchId);
    }



    @Override
    public List<Match> getAllMatchesByStatus(String status) throws NoMatchesFoundException {
        List<Match> list = matchRepository.findAllByStatus(status);
        if (list == null || list.isEmpty()) {
            throw new NoMatchesFoundException("No matches found with status: " + status);
        }
        return list;
    }
}

