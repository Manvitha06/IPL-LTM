package com.edutech.progressive.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.edutech.progressive.entity.Vote;
import com.edutech.progressive.repository.VoteRepository;

@Service
public class VoteServiceImpl {

    private final VoteRepository voteRepository;

    public VoteServiceImpl(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public ResponseEntity<List<Vote>> getAllVotes() {
        List<Vote> votes = voteRepository.findAll();
        return ResponseEntity.ok(votes);
    }

    public ResponseEntity<Integer> createVote(Vote vote) {
        Vote saved = voteRepository.save(vote);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved.getVoteId());
    }

    /**
     * Returns counts for each category:
     * "Team", "Batsman", "Bowler", "All-rounder", "Wicketkeeper"
     */
    public ResponseEntity<Map<String, Long>> getVotesCountOfAllCategories() {
        Map<String, Long> counts = new LinkedHashMap<>();
        counts.put("Team", voteRepository.countByCategory("Team"));
        counts.put("Batsman", voteRepository.countByCategory("Batsman"));
        counts.put("Bowler", voteRepository.countByCategory("Bowler"));
        counts.put("All-rounder", voteRepository.countByCategory("All-rounder"));
        counts.put("Wicketkeeper", voteRepository.countByCategory("Wicketkeeper"));
        return ResponseEntity.ok(counts);
    }
}



