package com.edutech.progressive.service;

import com.edutech.progressive.entity.Match;

import java.sql.SQLException;
import java.util.List;

public interface MatchService {
    List<Match> getAllMatches() throws SQLException;

    Match getMatchById(int matchId) throws SQLException;

    Integer addMatch(Match match) throws SQLException;

    void updateMatch(Match match) throws SQLException;

    void deleteMatch(int matchId) throws SQLException;

    // Default method as per spec (can be overridden in JPA impl)
    default List<Match> getAllMatchesByStatus(String status) {
        return null;
    }

}
