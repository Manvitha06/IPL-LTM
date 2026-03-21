package com.edutech.progressive.service.impl;
 
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.edutech.progressive.entity.Cricketer;
import com.edutech.progressive.entity.Team;
import com.edutech.progressive.exception.TeamCricketerLimitExceededException;
import com.edutech.progressive.repository.CricketerRepository;
import com.edutech.progressive.repository.TeamRepository;
import com.edutech.progressive.repository.VoteRepository;
import com.edutech.progressive.service.CricketerService;
@Service
public class CricketerServiceImplJpa implements CricketerService {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private VoteRepository voteRepository;
    private final CricketerRepository repo;
    @Autowired
    public CricketerServiceImplJpa(CricketerRepository repo) {
        this.repo = repo;
    }
 
    @Override
    public List<Cricketer> getAllCricketers() {
        return repo.findAll();
    }
 
    @Override
    public Integer addCricketer(Cricketer c) throws TeamCricketerLimitExceededException {
        if(repo.countByTeam_TeamId(c.getTeamId())==11){
            throw new TeamCricketerLimitExceededException("Team already has 11 players!");
        }
        return repo.save(c).getCricketerId();
    }
 
    @Override
    public List<Cricketer> getAllCricketersSortedByExperience() {
        List<Cricketer> list = repo.findAll();
        list.sort(Comparator.comparing(Cricketer::getExperience));
        return list;
    }
 
    @Override
    public void updateCricketer(Cricketer c) {
        Cricketer old= repo.findById(c.getCricketerId()).orElseThrow();
        old.setAge(c.getAge());
        old.setCricketerName(c.getCricketerName());
        old.setExperience(c.getExperience());
        old.setNationality(c.getNationality());
        old.setRole(c.getRole());
        old.setTeamId(c.getTeamId());
        old.setTotalRuns(c.getTotalRuns());
        old.setTotalWickets(c.getTotalWickets());
        repo.save(old);
    }
 

@Override
public void deleteCricketer(int id) {
    voteRepository.deleteByCricketerId(id);
        repo.deleteById(id);
}
 
    @Override
    public Cricketer getCricketerById(int id) {
        return repo.findByCricketerId(id);
    }
 
   @Override
public List<Cricketer> getCricketersByTeam(int teamId) {
    return repo.findByTeam_TeamId(teamId);
}
}