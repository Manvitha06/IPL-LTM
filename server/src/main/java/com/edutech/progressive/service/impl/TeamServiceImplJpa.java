package com.edutech.progressive.service.impl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.edutech.progressive.entity.Team;
import com.edutech.progressive.exception.TeamAlreadyExistsException;
import com.edutech.progressive.exception.TeamDoesNotExistException;
import com.edutech.progressive.repository.CricketerRepository;
import com.edutech.progressive.repository.MatchRepository;
import com.edutech.progressive.repository.TeamRepository;
import com.edutech.progressive.repository.VoteRepository;
import com.edutech.progressive.service.TeamService;
@Service
public class TeamServiceImplJpa implements TeamService { 


    private  TeamRepository teamRepository;
    private CricketerRepository cricketerRepository;
    private MatchRepository matchRepository;
    

    public TeamServiceImplJpa(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }
    @Autowired
    public TeamServiceImplJpa(TeamRepository teamRepository,
                              CricketerRepository cricketerRepository,
                              MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.cricketerRepository = cricketerRepository;
        this.matchRepository = matchRepository;
    }
   // @Autowired(required = false)
//private VoteRepository voteRepository;

    @Override
    public List<Team> getAllTeams() throws SQLException {
        return teamRepository.findAll();
    }

    @Override
    public int addTeam(Team team) throws TeamAlreadyExistsException {
        Team existing = teamRepository.findByTeamName(team.getTeamName());
        if (existing != null) {
            throw new TeamAlreadyExistsException(
                "Team with name '" + team.getTeamName() + "' already exists");
        }
        Team saved = teamRepository.save(team);
        return saved.getTeamId();
    }

    @Override
    public List<Team> getAllTeamsSortedByName() throws SQLException {
        List<Team> teams = teamRepository.findAll();
        Collections.sort(teams);
        return teams;
    }

    @Override
    public Team getTeamById(int teamId) throws TeamDoesNotExistException {
        Team team = teamRepository.findByTeamId(teamId);
        if (team == null) {
            throw new TeamDoesNotExistException("Team with ID " + teamId + " does not exist");
        }
        return team;
    }

    @Override
    public void updateTeam(Team team) throws TeamAlreadyExistsException {
        // Validate exists
        Team existing = teamRepository.findByTeamId(team.getTeamId());
        if (existing == null) {
            throw new TeamDoesNotExistException("Team with ID " + team.getTeamId() + " does not exist");
        }

        // Unique name check for another team
        Team withSameName = teamRepository.findByTeamName(team.getTeamName());
        if (withSameName != null && !Objects.equals(withSameName.getTeamId(), team.getTeamId())) {
            throw new TeamAlreadyExistsException(
                "Another team with name '" + team.getTeamName() + "' already exists");
        }

        teamRepository.save(team);
    }
    @Override
    public void deleteTeam(int teamId) throws SQLException {
        // Ensure exists
        Team team = teamRepository.findByTeamId(teamId);
        if (team == null) {
            throw new TeamDoesNotExistException("Team with ID " + teamId + " does not exist");
        }
        matchRepository.deleteByTeamId(teamId);
        cricketerRepository.deleteByTeamId(teamId);

        // Delete team
        teamRepository.deleteById(teamId);
    }
  


}