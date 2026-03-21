package com.edutech.progressive.dao;

import com.edutech.progressive.entity.Team;

import java.util.List;

public interface TeamDAO {
    int addTeam(Team team);
    Team getTeamById(int teamId);
    void updateTeam(Team team);
    void deleteTeam(int teamId) ;
    List<Team> getAllTeams();
}