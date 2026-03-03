package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Vote;
import com.edutech.progressive.service.impl.VoteServiceImpl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/vote")
@CrossOrigin
public class VoteController {

    private final VoteServiceImpl voteService;

    public VoteController(VoteServiceImpl voteService) {
        this.voteService = voteService;
    }

    // GET /vote
    @GetMapping
    public ResponseEntity<List<Vote>> getAllVotes() {
        return voteService.getAllVotes();
    }

    // POST /vote
    @PostMapping
    public ResponseEntity<Integer> createVote(@RequestBody Vote vote) {
        return voteService.createVote(vote);
    }

    // GET /vote/count
    @GetMapping("/count")
    public ResponseEntity<Map<String, Long>> getVotesCountOfAllCategories() {
        return voteService.getVotesCountOfAllCategories();
    }

}
