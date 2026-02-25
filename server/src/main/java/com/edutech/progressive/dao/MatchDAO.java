package com.edutech.progressive.dao;

import com.edutech.progressive.entity.Match;

import java.sql.SQLException;
import java.util.List;

public interface MatchDAO {
    int addMatch(Match match)throws SQLException;
    Match getMatchById(int matchId)throws SQLException;
    void updateMatch(Match match)throws SQLException;
    void deleteMatch(int matchId)throws SQLException;
    List<Match> getAllMatches()throws SQLException;
}