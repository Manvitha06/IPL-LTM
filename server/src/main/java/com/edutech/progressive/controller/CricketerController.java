package com.edutech.progressive.controller;
 
import com.edutech.progressive.entity.Cricketer;
import com.edutech.progressive.exception.TeamCricketerLimitExceededException;
import com.edutech.progressive.service.CricketerService;
import com.edutech.progressive.service.impl.CricketerServiceImplJpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import java.sql.SQLException;
import java.util.List;
 
 
@RestController
@RequestMapping("/cricketer")
public class CricketerController {
    @Autowired
    private CricketerServiceImplJpa service;
 
    // GET /cricketer
    @GetMapping
    public ResponseEntity<List<Cricketer>> getAllCricketers() {
            return ResponseEntity.ok(service.getAllCricketers());
    }
 
    // GET /cricketer/{cricketerId}
    @GetMapping("/{cricketerId}")
    public ResponseEntity<Cricketer> getCricketerById(@PathVariable int cricketerId) {
            return ResponseEntity.ok(service.getCricketerById(cricketerId));
    }
 
    // POST /cricketer
    @PostMapping
    public ResponseEntity<?> addCricketer(@RequestBody Cricketer cricketer) {
            // Normalize test payloads that send teamId = 0 (avoid Team#0 lookups)
            Integer id;
            try {
                id = service.addCricketer(cricketer);
            } catch (TeamCricketerLimitExceededException e) {
                return ResponseEntity.status(400).body(e.getMessage());
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }
 
    // PUT /cricketer/{cricketerId}
    @PutMapping("/{cricketerId}")
    public ResponseEntity<Void> updateCricketer(@PathVariable int cricketerId,
                                                @RequestBody Cricketer cricketer) {
           cricketer.setCricketerId(cricketerId);
            service.updateCricketer(cricketer);
            return ResponseEntity.ok().build();
    }
 
    // DELETE /cricketer/{cricketerId}
    @DeleteMapping("/{cricketerId}")
    public ResponseEntity<Void> deleteCricketer(@PathVariable int cricketerId) {
            // Service layer should ignore missing IDs (idempotent delete)
            service.deleteCricketer(cricketerId);
            return ResponseEntity.noContent().build();
    }
 
    // GET /cricketer/team/{teamId}
    @GetMapping("/team/{teamId}")
    public ResponseEntity<List<Cricketer>> getCricketersByTeamSimple(@PathVariable int teamId) {
            return ResponseEntity.ok(service.getCricketersByTeam(teamId));
    }
 
    // GET /cricketer/cricketer/team/{teamId}  (kept for compatibility with some Day-7 tests)
    @GetMapping("/cricketer/team/{teamId}")
    public ResponseEntity<List<Cricketer>> getCricketersByTeam(@PathVariable int teamId) {
            return ResponseEntity.ok(service.getCricketersByTeam(teamId));
    }
}