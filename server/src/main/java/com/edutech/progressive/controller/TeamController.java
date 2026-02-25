package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Team;
import com.edutech.progressive.service.TeamService;
import com.edutech.progressive.service.impl.TeamServiceImplArraylist;
import com.edutech.progressive.service.impl.TeamServiceImplJpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
@RestController
@RequestMapping("/team")
public class TeamController {
  
 

    private final TeamServiceImplJpa teamServiceJpa;
    private final TeamServiceImplArraylist teamServiceArraylist;

    @Autowired
    public TeamController(TeamServiceImplJpa teamServiceJpa, TeamServiceImplArraylist teamServiceArraylist) {
        this.teamServiceJpa = teamServiceJpa;
        this.teamServiceArraylist = teamServiceArraylist;
    }

    // GET /team - Returns a list of all teams (JPA)
    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams() throws SQLException {
        return ResponseEntity.ok(teamServiceJpa.getAllTeams());
    }


    // GET /team/{teamId} - Retrieves a team by its ID (JPA)
    @GetMapping("/{teamId}")
    public ResponseEntity<Team> getTeamById(@PathVariable int teamId) throws SQLException {
        return ResponseEntity.ok(teamServiceJpa.getTeamById(teamId));
    }

    // GET /team/fromArrayList - Returns all teams stored in the ArrayList (in-memory)
    @GetMapping("/fromArrayList")
    public ResponseEntity<List<Team>> getAllTeamsFromArrayList() throws SQLException {
        return ResponseEntity.ok(teamServiceArraylist.getAllTeams());
    }

    // GET /team/fromArrayList/sorted - Returns all teams from the ArrayList sorted by name
    @GetMapping("/fromArrayList/sorted")
    public ResponseEntity<List<Team>> getAllTeamsSortedByNameFromArrayList() throws SQLException {
        return ResponseEntity.ok(teamServiceArraylist.getAllTeamsSortedByName());
    }

    // POST /team - Adds a new team using JPA and returns the generated ID
    @PostMapping
    public ResponseEntity<Integer> addTeam(@RequestBody Team team) throws SQLException {
        return ResponseEntity.ok(teamServiceJpa.addTeam(team));
    }

    // POST /team/toArrayList - Adds a new team to the ArrayList and returns new list size (or ID)
    @PostMapping("/toArrayList")
    public ResponseEntity<Integer> addTeamToArrayList(@RequestBody Team team) throws SQLException {
        return ResponseEntity.status(201).body(teamServiceArraylist.addTeam(team));
    }

    // PUT /team/{teamId} - Updates the given team by its ID (JPA)
    @PutMapping("/{teamId}")
    public ResponseEntity< Void> updateTeam(@PathVariable int teamId, @RequestBody Team team) throws SQLException {
        // Ensure the path ID is used (overrides body if present)
        team.setTeamId(teamId);
        teamServiceJpa.updateTeam(team);
        return ResponseEntity.noContent().build();
    }

    // DELETE /team/{teamId} - Deletes a team by its ID (JPA)
    @DeleteMapping("/{teamId}")
    public ResponseEntity<Void> deleteTeam(@PathVariable int teamId) throws SQLException {
        teamServiceJpa.deleteTeam(teamId);
           return ResponseEntity.noContent().build();
    }
}
