package com.edutech.progressive.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.edutech.progressive.entity.Team;
import com.edutech.progressive.service.TeamService;
@Service
public class TeamServiceImplArraylist implements TeamService  {
    List<Team> teams= new ArrayList<>();
    @Override
    public List<Team> getAllTeams() {
        return teams;
    }
    @Override
    public int addTeam(Team team) {
        if(team!=null){
            teams.add(team);
            return teams.size();
        }
        return -1;
    }
    @Override
    public List<Team> getAllTeamsSortedByName() {
        List<Team> sorted= new ArrayList<>(teams);
        Collections.sort(sorted);
        return sorted;

    }
    @Override
    public void emptyArrayList() {
        teams.clear();
    }
}