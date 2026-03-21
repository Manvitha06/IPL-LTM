package com.edutech.progressive.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    private TicketBookingRepository ticketBookingRepository;
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
        return matchRepository.save(match).getMatchId();
    }

    @Override
    public void updateMatch(Match match) throws SQLException {
        Match old= matchRepository.findByMatchId(match.getMatchId());
        old.setMatchDate(match.getMatchDate());
        old.setFirstTeam(match.getFirstTeam());
        // old.setFirstTeamId(match.getFirstTeamId());
        old.setMatchDate(match.getMatchDate());
        old.setResult(match.getResult());
        // old.setSecondTeamId(match.getSecondTeamId());
        old.setSecondTeam(match.getSecondTeam());
        old.setStatus(match.getStatus());
        old.setVenue(match.getVenue());
        old.setWinnerTeamId(match.getWinnerTeamId());
        matchRepository.save(old);
    }

    @Override
    public void deleteMatch(int matchId) throws SQLException {
        ticketBookingRepository.deleteByMatchId(matchId);
        matchRepository.deleteById(matchId);
      
    }
    @Override
    public List<Match> getAllMatchesByStatus(String status) throws NoMatchesFoundException {
        if(matchRepository.findAllByStatus(status).isEmpty()){
            throw new NoMatchesFoundException("Matches with the given status not found");
        }
        return matchRepository.findAllByStatus(status);
    }
    

}