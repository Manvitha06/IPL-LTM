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



    private static final int TEAM_LIMIT = 11;

    private final CricketerRepository cricketerRepository;
    private final TeamRepository teamRepository;
   
private VoteRepository voteRepository; 


    @Autowired
    public CricketerServiceImplJpa(CricketerRepository cricketerRepository,
                                   TeamRepository teamRepository) {
        this.cricketerRepository = cricketerRepository;
        this.teamRepository = teamRepository;
    }
    @Override
    public List<Cricketer> getAllCricketers() throws SQLException {
        return cricketerRepository.findAll();
    }

    @Override
    public Integer addCricketer(Cricketer cricketer) throws TeamCricketerLimitExceededException {
        // Resolve and attach Team entity if needed
        Team team = null;
        if (cricketer.getTeam() != null && cricketer.getTeam().getTeamId() != 0) {
            team = teamRepository.findByTeamId(cricketer.getTeam().getTeamId());
        }
        if (team != null) {
            long count = cricketerRepository.countByTeam_TeamId(team.getTeamId());
            if (count >= TEAM_LIMIT) {
                throw new TeamCricketerLimitExceededException(
                    "Team " + team.getTeamName() + " already has " + TEAM_LIMIT + " cricketers");
            }
            cricketer.setTeam(team);
        }

        Cricketer saved = cricketerRepository.save(cricketer);
        return saved.getCricketerId();
    }

    @Override
    public List<Cricketer> getAllCricketersSortedByExperience() throws SQLException {
        List<Cricketer> list = cricketerRepository.findAll();
        list.sort(Comparator.comparingInt(Cricketer::getExperience));
        return list;
    }

    @Override
    public void updateCricketer(Cricketer cricketer) throws SQLException {
        cricketerRepository.save(cricketer);
    }

    @Override
    public void deleteCricketer(int cricketerId) throws SQLException {
      
 if (voteRepository != null) {
        voteRepository.deleteByCricketerId(cricketerId);
    }
     cricketerRepository.deleteById(cricketerId);
    }

    @Override
    public Cricketer getCricketerById(int cricketerId) throws SQLException {
        return cricketerRepository.findByCricketerId(cricketerId);
    }

    @Override
    public List<Cricketer> getCricketersByTeam(int teamId) throws SQLException {
        return cricketerRepository.findByTeamId(teamId);
    }

    
}



    
