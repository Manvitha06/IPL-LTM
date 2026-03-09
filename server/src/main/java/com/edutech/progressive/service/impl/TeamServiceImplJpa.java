// src/main/java/com/edutech/progressive/service/impl/TeamServiceImplJpa.java
package com.edutech.progressive.service.impl;

import com.edutech.progressive.entity.Team;
import com.edutech.progressive.exception.TeamAlreadyExistsException;
import com.edutech.progressive.exception.TeamDoesNotExistException;
import com.edutech.progressive.repository.*;
import com.edutech.progressive.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class TeamServiceImplJpa implements TeamService {

    // ✅ Field injection so Spring can wire deps with no-arg ctor
    @Autowired
    private TeamRepository teamRepository;

    @Autowired(required = false)
    private CricketerRepository cricketerRepository;        // optional for tests

    @Autowired(required = false)
    private MatchRepository matchRepository;                 // optional for tests

    @Autowired(required = false)
    private TicketBookingRepository ticketBookingRepository; // optional for tests

    @Autowired(required = false)
    private VoteRepository voteRepository;                   // optional for tests

    // ✅ Needed by Spring test contexts that expect a default ctor
    public TeamServiceImplJpa() {}

    // ✅ Needed by DayThirteenTest which calls new TeamServiceImplJpa(teamRepository)
    public TeamServiceImplJpa(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

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
        return teamRepository.save(team).getTeamId();
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
        Team existing = teamRepository.findByTeamId(team.getTeamId());
        if (existing == null) {
            throw new TeamDoesNotExistException("Team with ID " + team.getTeamId() + " does not exist");
        }
        Team sameName = teamRepository.findByTeamName(team.getTeamName());
        if (sameName != null && !Objects.equals(sameName.getTeamId(), team.getTeamId())) {
            throw new TeamAlreadyExistsException(
                "Another team with name '" + team.getTeamName() + "' already exists");
        }
        teamRepository.save(team);
    }

    @Override
public void deleteTeam(int teamId) throws SQLException {
    Team team = teamRepository.findByTeamId(teamId);
    if (team == null) {
        throw new TeamDoesNotExistException("Team with ID " + teamId + " does not exist");
    }

    // ✅ delete children first (null-safe for tests that inject only TeamRepository)
    if (voteRepository != null)            voteRepository.deleteByTeamId(teamId);
    if (ticketBookingRepository != null)   ticketBookingRepository.deleteByTeamId(teamId);
    if (matchRepository != null)           matchRepository.deleteByTeamId(teamId);
    if (cricketerRepository != null)       cricketerRepository.deleteByTeamId(teamId);

    teamRepository.deleteById(teamId);
}
    }
