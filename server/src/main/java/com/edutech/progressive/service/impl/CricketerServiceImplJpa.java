package com.edutech.progressive.service.impl;

import com.edutech.progressive.entity.Cricketer;
import com.edutech.progressive.entity.Team;
import com.edutech.progressive.exception.TeamCricketerLimitExceededException;
import com.edutech.progressive.repository.CricketerRepository;
import com.edutech.progressive.repository.TeamRepository;
import com.edutech.progressive.repository.VoteRepository;
import com.edutech.progressive.service.CricketerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

@Service
public class CricketerServiceImplJpa implements CricketerService {

    private static final int TEAM_LIMIT = 11;

    @Autowired
    private CricketerRepository cricketerRepository;

    // MAY be null in tests that don’t pass it – handle null safely
    @Autowired(required = false)
    private TeamRepository teamRepository;

    @Autowired(required = false)
    private VoteRepository voteRepository;

    // Required by some Spring test contexts
    public CricketerServiceImplJpa() {}

    // ✅ Added: matches DayThirteenTest new CricketerServiceImplJpa(cricketerRepository)
    public CricketerServiceImplJpa(CricketerRepository cricketerRepository) {
        this.cricketerRepository = cricketerRepository;
    }

    // Still keep the full constructor for normal app wiring
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
        // Only enforce team-limit if teamRepository is available
        if (teamRepository != null && cricketer.getTeam() != null && cricketer.getTeam().getTeamId() != 0) {
            Team team = teamRepository.findByTeamId(cricketer.getTeam().getTeamId());
            if (team != null) {
                long count = cricketerRepository.countByTeam_TeamId(team.getTeamId());
                if (count >= TEAM_LIMIT) {
                    throw new TeamCricketerLimitExceededException(
                            "Team " + team.getTeamName() + " already has " + TEAM_LIMIT + " cricketers");
                }
                cricketer.setTeam(team);
            }
        }
        return cricketerRepository.save(cricketer).getCricketerId();
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
        // Works regardless of teamRepository being present
        return cricketerRepository.findByTeamId(teamId);
    }
}