package com.edutech.progressive.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.edutech.progressive.dao.TeamDAO;
import com.edutech.progressive.entity.Team;
import com.edutech.progressive.service.TeamService;

public class TeamServiceImplJdbc implements TeamService  {
    TeamDAO teamDAO;

    
    public TeamServiceImplJdbc(TeamDAO teamDAO) {
      this.teamDAO = teamDAO;
    }

    @Override
    public List<Team> getAllTeams() {
     return teamDAO.getAllTeams();
    }

    @Override
    public int addTeam(Team team) {
      return teamDAO.addTeam(team);
    }

    @Override
    public List<Team> getAllTeamsSortedByName() {
      List<Team> sorted= teamDAO.getAllTeams();
      Collections.sort(sorted);
      return sorted;
    }
    @Override
    public Team getTeamById(int teamId) {
        return teamDAO.getTeamById(teamId);
    }
    @Override
    public void updateTeam(Team team) {
      teamDAO.updateTeam(team);
    }
    @Override
    public void deleteTeam(int teamId) {
      teamDAO.deleteTeam(teamId);
    }
    
}