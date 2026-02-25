package com.edutech.progressive.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.edutech.progressive.entity.Team;

@Service
public class TeamServiceImplJpa  {
    List<Team> t=new ArrayList<>();
    public List<Team> getAllTeams()throws SQLException
    {
        return t;
    }
    public int addTeam(Team team)throws SQLException
    {
         t.add(team);
         return t.size();
    }
    List<Team> getAllTeamsSortedByName()throws SQLException
    {
        Collections.sort(t);
        return t;
    }
    public Team getTeamById(int teamId)throws SQLException
    {for (Team team : t) {
        if(team.getTeamId()==teamId)
        {
            return team;
        }
        
    }return null;
        
    }
    public void updateTeam(Team team)throws SQLException
    {
        for (Team team1 : t) {
            if(team1.getTeamId()==team.getTeamId())
            {
                team1.setTeamName(team.getTeamName());
                team1.setLocation(team.getLocation());
                team1.setOwnerName(team.getOwnerName());
                team1.setEstablishmentYear(team.getEstablishmentYear());
            }   
        }
    }
  public void deleteTeam(int teamId)
  {
  
  t.removeIf(team -> team.getTeamId() == teamId);
}

  }

