package com.edutech.progressive.dao;

import com.edutech.progressive.entity.Team;

import java.sql.SQLException;
import java.util.List;

public interface TeamDAO {
    int addTeam(Team team)throws SQLException;
    Team getTeamById(int teamId)throws SQLException;
    void updateTeam(Team team)throws SQLException;
    void deleteTeam(int teamId) throws SQLException ;
    List<Team> getAllTeams()throws SQLException;
}
