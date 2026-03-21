package com.edutech.progressive.service;

import com.edutech.progressive.entity.Team;
import com.edutech.progressive.exception.TeamAlreadyExistsException;
import com.edutech.progressive.exception.TeamDoesNotExistException;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;
@Service
public interface TeamService {

    List<Team> getAllTeams() throws SQLException;

    int addTeam(Team team) throws SQLException, TeamAlreadyExistsException;

    List<Team> getAllTeamsSortedByName() throws SQLException;

    default void emptyArrayList() {
    }

    //Do not implement these methods in TeamServiceImplArrayList.java class
    default Team getTeamById(int teamId) throws SQLException, TeamDoesNotExistException {
        return null;
    }

    default void updateTeam(Team team) throws SQLException, TeamDoesNotExistException, TeamAlreadyExistsException {}

    default void deleteTeam(int teamId) throws SQLException {}

}