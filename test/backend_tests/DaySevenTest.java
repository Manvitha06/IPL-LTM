package com.edutech.progressive;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.edutech.progressive.entity.Match;
import com.edutech.progressive.entity.Team;
import com.edutech.progressive.entity.Cricketer;
import com.edutech.progressive.repository.MatchRepository;
import com.edutech.progressive.repository.TeamRepository;
import com.edutech.progressive.repository.CricketerRepository;
import com.edutech.progressive.service.MatchService;
import com.edutech.progressive.service.TeamService;
import com.edutech.progressive.service.CricketerService;
import com.edutech.progressive.service.impl.MatchServiceImplJpa;
import com.edutech.progressive.service.impl.TeamServiceImplJpa;
import com.edutech.progressive.service.impl.CricketerServiceImplJpa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = IplApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class DaySevenTest {

    private ObjectMapper objectMapper;

    @Autowired
    MatchRepository matchRepository;
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    CricketerRepository cricketerRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp() throws SQLException {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        String url = "jdbc:mysql://localhost:3306/mydb";
        String user = "root";
        String password = "root";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {
            String deleteQuery = "DELETE FROM matches";
            statement.executeUpdate(deleteQuery);

            deleteQuery = "DELETE FROM cricketer";
            statement.executeUpdate(deleteQuery);

            deleteQuery = "DELETE FROM team";
            statement.executeUpdate(deleteQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        MockitoAnnotations.openMocks(this);
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
        setDynamicProperty(cricketer, "team", team);
        cricketer.setCricketerName(cricketerName);
        cricketer.setExperience(experience);
        return cricketer;
    }

    Match getMatchObject(Integer id, Team firstTeam, Team secondTeam) {
        Match match = new Match();
        if ( id != null) {
            match.setMatchId(id);
        }
        setDynamicProperty(match, "firstTeam", firstTeam);
        setDynamicProperty(match, "secondTeam", secondTeam);
        match.setMatchDate(new java.util.Date());
        return match;
    }

    public void setDynamicProperty(Object entity, String propertyName, Object value) {
        try {
            Field field = entity.getClass().getDeclaredField(propertyName);
            field.setAccessible(true);
            field.set(entity, value);
        } catch (Exception e) {
            // Handle exception
        }
    }

    // Day - 6 Test cases

    // @Test
    // public void testAddTeamController_Day6() throws Exception {
    //     TeamService teamService = new TeamServiceImplJpa(teamRepository);
    //     Team team = getTeamObject(1, "Mumbai Indians");

    //     mockMvc.perform(post("/team")
    //                     .contentType(MediaType.APPLICATION_JSON)
    //                     .content(objectMapper.writeValueAsString(team)))
    //             .andExpect(status().isCreated());
    //     List<Team> teams = teamRepository.findAll();
    //     assertEquals(1, teams.size());
    //     assertEquals("Mumbai Indians", teams.get(0).getTeamName());
    // }

    // @Test
    // public void testUpdateTeamController_Day6() throws Exception {
    //     TeamService teamService = new TeamServiceImplJpa(teamRepository);
    //     Team team = getTeamObject(null, "Mumbai Indians");
    //     team = teamRepository.save(team);

    //     team.setTeamName("MI");

    //     mockMvc.perform(put("/team/" + team.getTeamId())
    //                     .contentType(MediaType.APPLICATION_JSON)
    //                     .content(objectMapper.writeValueAsString(team)))
    //             .andExpect(status().isOk());

    //     Team teamUpdated = teamRepository.findByTeamId(team.getTeamId());
    //     assertEquals(teamUpdated.getTeamName(), team.getTeamName());
    // }

    // @Test
    // public void testDeleteTeamController_Day6() throws Exception {
    //     TeamService teamService = new TeamServiceImplJpa(teamRepository);
    //     Team team1 = getTeamObject(null, "Mumbai Indians");
    //     team1.setTeamId(teamService.addTeam(team1));

    //     mockMvc.perform(delete("/team/" + team1.getTeamId()))
    //             .andExpect(status().isNoContent());
    // }

    // @Test
    // void testGetAllTeam_Day6() throws SQLException {
    //     TeamService teamService = new TeamServiceImplJpa(teamRepository);
    //     Team team1 = getTeamObject(null, "Mumbai Indians");
    //     Team team2 = getTeamObject(null, "CSK");
    //     teamService.addTeam(team1);
    //     teamService.addTeam(team2);

    //     List<Team> result = teamService.getAllTeams();

    //     assertEquals(2, result.size());
    // }

    // @Test
    // void testGetTeamById_Day6() throws SQLException {
    //     TeamService teamService = new TeamServiceImplJpa(teamRepository);

    //     Team team1 = getTeamObject(null, "Mumbai Indians");
    //     int id = teamService.addTeam(team1);

    //     Team result = teamService.getTeamById(id);

    //     assertEquals(result.getTeamId(), id);
    //     assertEquals("Mumbai Indians", result.getTeamName());
    // }

    // Day - 7 Test cases

    @Test
    void testGetAllCricketer_Day7() throws SQLException {
        CricketerService cricketerService = new CricketerServiceImplJpa(cricketerRepository);
        TeamService teamService = new TeamServiceImplJpa(teamRepository);
        Team team1 = getTeamObject(null, "Mumbai Indians");
        int id = teamService.addTeam(team1);
        team1.setTeamId(id);
        Cricketer cricketers1 = getCricketerObject(null, team1, "Dhoni",  20);
        Cricketer cricketers2 = getCricketerObject(null, team1, "Virat", 15);
        cricketerService.addCricketer(cricketers1);
        cricketerService.addCricketer(cricketers2);

        List<Cricketer> result = cricketerService.getAllCricketers();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testGetCricketersByTeam_Day7() throws SQLException {
        CricketerService cricketerService = new CricketerServiceImplJpa(cricketerRepository);
        TeamService teamService = new TeamServiceImplJpa(teamRepository);
        Team team1 = getTeamObject(null, "Mumbai Indians");
        int id = teamService.addTeam(team1);
        team1.setTeamId(id);
        Cricketer cricketers1 = getCricketerObject(null, team1, "Dhoni",  20);
        cricketers1.setCricketerId(cricketerService.addCricketer(cricketers1));

        List<Cricketer> result = cricketerService.getCricketersByTeam(id);

        assertNotNull(result);
        assertEquals(cricketers1.getCricketerId(), result.get(0).getCricketerId());
    }

    @Test
    void testGetAllCricketersSortedByExperience_Day7() throws SQLException {
        CricketerService cricketerService = new CricketerServiceImplJpa(cricketerRepository);
        TeamService teamService = new TeamServiceImplJpa(teamRepository);
        Team team1 = getTeamObject(null, "Mumbai Indians");
        int id = teamService.addTeam(team1);
        team1.setTeamId(id);
        Cricketer cricketers1 = getCricketerObject(null, team1, "Dhoni",  20);
        Cricketer cricketers2 = getCricketerObject(null, team1, "Virat", 15);
        Cricketer cricketers3 = getCricketerObject(null, team1, "Warner", 14);
        cricketerService.addCricketer(cricketers1);
        cricketerService.addCricketer(cricketers2);
        cricketerService.addCricketer(cricketers3);

        List<Cricketer> sortedCricketer = cricketerService.getAllCricketersSortedByExperience();

        assertTrue(sortedCricketer.get(0).getExperience() < sortedCricketer.get(1).getExperience());
    }

    @Test
    void testUpdateCricketerController_Day7() throws Exception {
        CricketerService cricketerService = new CricketerServiceImplJpa(cricketerRepository);
        TeamService teamService = new TeamServiceImplJpa(teamRepository);
        Team team1 = getTeamObject(null, "Mumbai Indians");
        int id = teamService.addTeam(team1);
        team1.setTeamId(id);
        Cricketer cricketers1 = getCricketerObject(null, team1, "Dhoni",  20);
        int crickId = cricketerService.addCricketer(cricketers1);
        cricketers1.setCricketerId(crickId);

        cricketers1.setExperience(22);
        mockMvc.perform(put("/cricketer/" + crickId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cricketers1)))
                .andExpect(status().isOk());
        Cricketer updatedCricketer = cricketerService.getCricketerById(crickId);
        assertEquals(22, updatedCricketer.getExperience());
    }

    @Test
    void testDeleteCricketerController_Day7() throws Exception {
        CricketerService cricketerService = new CricketerServiceImplJpa(cricketerRepository);
        TeamService teamService = new TeamServiceImplJpa(teamRepository);
        Team team1 = getTeamObject(null, "Mumbai Indians");
        int id = teamService.addTeam(team1);
        team1.setTeamId(id);
        Cricketer cricketers1 = getCricketerObject(null, team1, "Dhoni",  20);
        int crickId = cricketerService.addCricketer(cricketers1);

        mockMvc.perform(delete("/cricketer/" + crickId).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        assertNull(cricketerService.getCricketerById(crickId));
    }

    // Day - 8 Test cases

    // @Test
    // public void testGetAllMatchesController_Day8() throws Exception {
    //     MatchService matchService = new MatchServiceImplJpa(matchRepository);
    //     TeamService teamService = new TeamServiceImplJpa(teamRepository);
    //     Team team1 = getTeamObject(null, "Mumbai Indians");
    //     team1.setTeamId(teamService.addTeam(team1));
    //     Team team2 = getTeamObject(null, "CSK");
    //     team2.setTeamId(teamService.addTeam(team2));

    //     Match match1 = getMatchObject(null, team1, team2);
    //     Match match2 = getMatchObject(null, team2, team1);
    //     Match match3 = getMatchObject(null, team1, team2);
    //     matchService.addMatch(match1);
    //     matchService.addMatch(match2);
    //     matchService.addMatch(match3);

    //     List<Match> matchList = matchService.getAllMatches();
    //     mockMvc.perform(get("/match").contentType(MediaType.APPLICATION_JSON))
    //             .andExpect(status().isOk())
    //             .andExpect(jsonPath("$.size()").value(3));
    // }

    // @Test
    // public void testGetAllMatchesByStatusController_Day8() throws Exception {
    //     MatchService matchService = new MatchServiceImplJpa(matchRepository);
    //     TeamService teamService = new TeamServiceImplJpa(teamRepository);
    //     Team team1 = getTeamObject(null, "Mumbai Indians");
    //     team1.setTeamId(teamService.addTeam(team1));
    //     Team team2 = getTeamObject(null, "CSK");
    //     team2.setTeamId(teamService.addTeam(team2));

    //     Match match1 = getMatchObject(null, team1, team2);
    //     match1.setStatus("Scheduled");
    //     match1.setMatchId(matchService.addMatch(match1));

    //     mockMvc.perform(get("/match/status/Scheduled").contentType(MediaType.APPLICATION_JSON))
    //             .andExpect(status().isOk())
    //             .andExpect(jsonPath("$.size()").value(1));
    // }

    // @Test
    // public void testUpdateMatch_Day8() throws Exception {
    //     MatchService matchService = new MatchServiceImplJpa(matchRepository);
    //     TeamService teamService = new TeamServiceImplJpa(teamRepository);
    //     Team team1 = getTeamObject(null, "Mumbai Indians");
    //     team1.setTeamId(teamService.addTeam(team1));
    //     Team team2 = getTeamObject(null, "CSK");
    //     team2.setTeamId(teamService.addTeam(team2));

    //     Match match1 = getMatchObject(null, team1, team2);
    //     match1.setStatus("Scheduled");
    //     match1.setMatchId(matchService.addMatch(match1));

    //     match1.setStatus("Completed");
    //     matchService.updateMatch(match1);

    //     Match updatedMatch = matchService.getMatchById(match1.getMatchId());
    //     assertNotNull(updatedMatch);
    //     assertEquals("Completed", match1.getStatus());
    // }

    // @Test
    // public void testDeleteMatch_Day8() throws Exception {
    //     MatchService matchService = new MatchServiceImplJpa(matchRepository);
    //     TeamService teamService = new TeamServiceImplJpa(teamRepository);
    //     Team team1 = getTeamObject(null, "Mumbai Indians");
    //     team1.setTeamId(teamService.addTeam(team1));
    //     Team team2 = getTeamObject(null, "CSK");
    //     team2.setTeamId(teamService.addTeam(team2));

    //     Match match1 = getMatchObject(null, team1, team2);

    //     int matchId = matchService.addMatch(match1);

    //     assertNotNull(matchId);

    //     assertNotEquals(-1, matchId);
    //     assertNotEquals(0, matchId);

    //     matchService.deleteMatch(matchId);

    //     assertNull(matchService.getMatchById(matchId));

    // }
}
