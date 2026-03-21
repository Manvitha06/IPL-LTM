package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Match;
import com.edutech.progressive.exception.NoMatchesFoundException;
import com.edutech.progressive.service.impl.MatchServiceImplJpa;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/match")
public class MatchController {
    private MatchServiceImplJpa matchServiceImplJpa;
    @Autowired
    public MatchController(MatchServiceImplJpa matchServiceImplJpa) {
        this.matchServiceImplJpa = matchServiceImplJpa;
    }
    @GetMapping
    public ResponseEntity<List<Match>> getAllMatches() throws SQLException {
        return ResponseEntity.ok(matchServiceImplJpa.getAllMatches());
    }
    @GetMapping("/{matchId}")
    public ResponseEntity<Match> getMatchById(@PathVariable int matchId) throws SQLException {
        return ResponseEntity.ok(matchServiceImplJpa.getMatchById(matchId));
    }
    @PostMapping
    public ResponseEntity<Integer> addMatch(@RequestBody Match match) throws SQLException {
        return ResponseEntity.ok(matchServiceImplJpa.addMatch(match));
    }
    @PutMapping("/{matchId}")
    public ResponseEntity<Void> updateMatch(@PathVariable int matchId, @RequestBody Match match) throws SQLException {
        match.setMatchId(matchId);
        matchServiceImplJpa.updateMatch(match);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{matchId}")
    public ResponseEntity<Void> deleteMatch(@PathVariable int matchId) throws SQLException {
        matchServiceImplJpa.deleteMatch(matchId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/status/{status}")
    public ResponseEntity<?> getAllMatchesByStatus(@PathVariable String status) {
        
        try {
            return ResponseEntity.ok(matchServiceImplJpa.getAllMatchesByStatus(status));
        } catch (NoMatchesFoundException e) {
           return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}