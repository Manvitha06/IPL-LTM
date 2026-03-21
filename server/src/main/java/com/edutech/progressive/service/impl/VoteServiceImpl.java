package com.edutech.progressive.service.impl;
 
import com.edutech.progressive.entity.Vote;
import com.edutech.progressive.repository.VoteRepository;
import com.edutech.progressive.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
@Service
public class VoteServiceImpl implements VoteService {
 
    @Autowired
    VoteRepository voteRepository;
 
    @Override
    public List<Vote> getAllVotes() {
        return voteRepository.findAll();
    }
 
    @Override
    public int createVote(Vote vote) {
        return voteRepository.save(vote).getVoteId();
    }
 
    @Override
    public Map<String, Long> getVotesCountOfAllCategories() {
        Map<String, Long> countsMap = new HashMap<>();
        List<String> categories = List.of("Team", "Cricketer");
        for (String category : categories) {
            Long count = voteRepository.countByCategory(category);
            countsMap.put(category, count);
        }
        return countsMap;
    }
 
}