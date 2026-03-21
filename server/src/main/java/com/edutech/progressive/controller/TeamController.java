package com.edutech.progressive.controller;
import com.edutech.progressive.entity.Team;
import com.edutech.progressive.exception.TeamAlreadyExistsException;
import com.edutech.progressive.exception.TeamDoesNotExistException;
import com.edutech.progressive.service.TeamService;
import com.edutech.progressive.service.impl.TeamServiceImplArraylist;
import com.edutech.progressive.service.impl.TeamServiceImplJpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @Autowired
    private TeamServiceImplJpa teamServiceJpa;
    @Autowired
    private TeamServiceImplArraylist teamServiceArraylist;
    // public TeamController(TeamService teamServiceJpa) {
    //     this.teamServiceJpa = teamServiceJpa;
    // }
    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams() throws SQLException {
        return ResponseEntity.ok(teamServiceJpa.getAllTeams());
    }
    @GetMapping("/{teamId}")
    public ResponseEntity<?> getTeamById(@PathVariable int teamId) throws SQLException {
        Team t1= new Team();
        try {
            t1= teamServiceJpa.getTeamById(teamId);
        } 
         catch (TeamDoesNotExistException e) {
          return ResponseEntity.status(404).body(e.getMessage());
        }
        return ResponseEntity.ok(t1);
    }
    @PostMapping
    public ResponseEntity<?> addTeam(@RequestBody Team team) throws SQLException {
        int id=0;
        try {
            id = teamServiceJpa.addTeam(team);
        } 
         catch (TeamAlreadyExistsException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(id); // ✅ 201
    }
    @PutMapping("/{teamId}")
    public ResponseEntity<?> updateTeam(@PathVariable int teamId, @RequestBody Team team) throws SQLException {
        team.setTeamId(teamId);
            try {
                teamServiceJpa.updateTeam(team);
            } catch (TeamDoesNotExistException | TeamAlreadyExistsException e) {
                return ResponseEntity.status(400).body(e.getMessage());
            }
       
        return ResponseEntity.ok().build(); // 200
    }
    @DeleteMapping("/{teamId}")
    public ResponseEntity<Void> deleteTeam(@PathVariable int teamId) throws SQLException {
        teamServiceJpa.deleteTeam(teamId);
        return ResponseEntity.noContent().build(); // 204
    }
 
 
    @GetMapping("/fromArrayList")
    public ResponseEntity<List<Team>> getAllTeamsFromArrayList() throws SQLException {
        return ResponseEntity.ok(teamServiceArraylist.getAllTeams());
    }
 
    @GetMapping("/fromArrayList/sorted")
    public ResponseEntity<List<Team>> getAllTeamsSortedByNameFromArrayList() throws SQLException {
        return ResponseEntity.ok(teamServiceArraylist.getAllTeamsSortedByName());
    }
 
    @PostMapping("/toArrayList")
    public ResponseEntity<Integer> addTeamToArrayList(@RequestBody Team team) throws SQLException {
        int size = teamServiceArraylist.addTeam(team);
        return ResponseEntity.status(HttpStatus.CREATED).body(size);
    }
}