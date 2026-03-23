package com.edutech.progressive.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.progressive.entity.Team;
import com.edutech.progressive.exception.TeamAlreadyExistsException;
import com.edutech.progressive.exception.TeamDoesNotExistException;
import com.edutech.progressive.repository.CricketerRepository;
import com.edutech.progressive.repository.MatchRepository;
import com.edutech.progressive.repository.TeamRepository;
import com.edutech.progressive.repository.TicketBookingRepository;
import com.edutech.progressive.repository.VoteRepository;
import com.edutech.progressive.service.TeamService;
@Service
public class TeamServiceImplJpa implements TeamService {
    @Autowired
    private MatchRepository matchRepo;
    @Autowired
    private CricketerRepository cricketerRepository;
    private TeamRepository teamRepository;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private TicketBookingRepository ticketBookingRepository;
    @Autowired
    public TeamServiceImplJpa(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }
    public List<Team> getAllTeams() throws SQLException{
        return teamRepository.findAll();
    }
    public int addTeam(Team team) throws SQLException, TeamAlreadyExistsException{
       Team t1=teamRepository.findByTeamName(team.getTeamName());
        if(t1!=null){
            throw new TeamAlreadyExistsException("Team with the name already exists!");
        }
        return teamRepository.save(team).getTeamId();
    }
    public List<Team> getAllTeamsSortedByName() throws SQLException{
        List<Team> teams= teamRepository.findAll();
        teams.sort(Comparator.comparing(Team:: getTeamName));
        return teams;
    }
    public Team getTeamById(int teamId) throws SQLException, TeamDoesNotExistException{
        Team t1= teamRepository.findByTeamId(teamId);
        if(t1==null){
            throw new TeamDoesNotExistException("Team with given ID does not exist!");
        }
        return t1;
    }
    public void updateTeam(Team team) throws SQLException, TeamDoesNotExistException, TeamAlreadyExistsException{
        Team old= teamRepository.findById(team.getTeamId()).orElseThrow(()-> new TeamDoesNotExistException("Team with given ID Does not exist!"));
        //  if(old.getTeamName().equals(teamRepository.findByTeamName(team.getTeamName()).getTeamName())){
        //     throw new TeamAlreadyExistsException("Team with the name already exists!");
        // }
        old.setEstablishmentYear(team.getEstablishmentYear());
        old.setLocation(team.getLocation());
        old.setOwnerName(team.getOwnerName());
        old.setTeamName(team.getTeamName());
        teamRepository.save(team);

    }
    public void deleteTeam (int teamId) throws SQLException{
        matchRepo.deleteByFirstTeamId(teamId);
        matchRepo.deleteBySecondTeamId(teamId);
        cricketerRepository.deleteByTeamId(teamId);
        voteRepository.deleteByTeamId(teamId);
        ticketBookingRepository.deleteByTeamId(teamId);
        teamRepository.deleteById(teamId);
    }
}