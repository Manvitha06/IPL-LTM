package com.edutech.progressive.service.impl;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.edutech.progressive.dao.TeamDAO;
import com.edutech.progressive.entity.Team;
import com.edutech.progressive.service.TeamService;

public class TeamServiceImplJdbc implements TeamService  {



    private final TeamDAO teamDAO;

    public TeamServiceImplJdbc(TeamDAO teamDAO) {
        this.teamDAO = teamDAO;
    }

    @Override
    public List<Team> getAllTeams() throws SQLException {
        return teamDAO.getAllTeams();
    }

    @Override
    public int addTeam(Team team) throws SQLException {
        return teamDAO.addTeam(team);
    }

    @Override
    public List<Team> getAllTeamsSortedByName() throws SQLException {
        return teamDAO.getAllTeams()
                .stream()
                .sorted(Comparator.comparing(Team::getTeamName, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
    }

    @Override
    public Team getTeamById(int teamId) throws SQLException {
        return teamDAO.getTeamById(teamId);
    }

    @Override
    public void updateTeam(Team team) throws SQLException {
        teamDAO.updateTeam(team);
    }

    @Override
    public void deleteTeam(int teamId) throws SQLException {
        teamDAO.deleteTeam(teamId);
    }
}

   