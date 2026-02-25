package com.edutech.progressive;

import com.edutech.progressive.entity.Cricketer;
import com.edutech.progressive.entity.Team;
import com.edutech.progressive.entity.Vote;
import com.edutech.progressive.repository.CricketerRepository;
import com.edutech.progressive.repository.MatchRepository;
import com.edutech.progressive.repository.TeamRepository;
import com.edutech.progressive.repository.VoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.SQLException;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = IplApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class DayTenTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    CricketerRepository cricketerRepository;

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() throws SQLException {
        objectMapper = new ObjectMapper();
        voteRepository.deleteAll();
        matchRepository.deleteAll();
        cricketerRepository.deleteAll();
        teamRepository.deleteAll();
    }

    // Helper classes to create Objects
    Team getTeamObject(Integer id, String name) {
        Team team = new Team();
        if (id != null) {
            team.setTeamId(id.intValue());
        }
        team.setTeamName(name);
        team.setLocation("Mumbai");
        team.setOwnerName("Ambani");
        team.setEstablishmentYear(2014);
        return team;
    }

    Cricketer getCricketerObject(Integer id, Team team, String cricketerName, int experience) {
        Cricketer cricketer = new Cricketer();
        if (id != null) {
            cricketer.setCricketerId(id.intValue());
        }
        cricketer.setTeam(team);
        cricketer.setCricketerName(cricketerName);
        cricketer.setExperience(experience);
        return cricketer;
    }

    Vote getVoteObject(Integer id, String email, String category, Cricketer cricketer, Team team) {
        Vote vote = new Vote();
        if ( id != null ) {
            vote.setVoteId(id);
        }
        vote.setEmail(email);
        vote.setCategory(category);
        vote.setCricketer(cricketer);
        vote.setTeam(team);
        return vote;
    }

    @Test
    void testGetAllVotesController_Day10() throws Exception {
        Team team = getTeamObject(null, "Mumbai Indians");
        team.setTeamId(teamRepository.save(team).getTeamId());
        Cricketer cricketer = getCricketerObject(null, team, "Dhoni",  20);
        cricketer.setCricketerId(cricketerRepository.save(cricketer).getCricketerId());

        Vote vote1 = getVoteObject(null, "user1@example.com", "Team", null, team);
        Vote vote2 = getVoteObject(null, "user2@example.com", "Batsman", cricketer, null);
        voteRepository.save(vote2);
        voteRepository.save(vote1);

        mockMvc.perform(get("/vote"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value( 2));
    }

    @Test
    void testCreateVoteController_Day10() throws Exception {
        Team team = getTeamObject(null, "Mumbai Indians");
        team = teamRepository.save(team);
        Vote vote = getVoteObject(null, "user1@example.com", "Team", null, team);

        mockMvc.perform(post("/vote")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vote)))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetVotesCountByAllCategory_Day10() throws Exception {
        Team team = getTeamObject(null, "Mumbai Indians");
        team = teamRepository.save(team);
        Vote vote1 = getVoteObject(null, "user1@example.com", "Team", null, team);
        Vote vote2 = getVoteObject(null, "user2@example.com", "Team", null, team);
        Vote vote3 = getVoteObject(null, "user3@example.com", "Team", null, team);
        Vote vote4 = getVoteObject(null, "user4@example.com", "Team", null, team);
        Vote vote5 = getVoteObject(null, "user5@example.com", "Team", null, team);
        List<Vote> voteList = List.of(vote1, vote2, vote3, vote4, vote5);
        voteRepository.saveAll(voteList);

        mockMvc.perform(get("/vote/count"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Team").value( 5));
    }

}
