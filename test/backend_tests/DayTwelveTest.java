package com.edutech.progressive;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.edutech.progressive.entity.Cricketer;
import com.edutech.progressive.entity.Match;
import com.edutech.progressive.entity.Team;
import com.edutech.progressive.entity.User;
import com.edutech.progressive.jwt.JwtUtil;
import com.edutech.progressive.repository.*;
import com.edutech.progressive.service.CricketerService;
import com.edutech.progressive.service.MatchService;
import com.edutech.progressive.service.TeamService;
import com.edutech.progressive.service.impl.CricketerServiceImplJpa;
import com.edutech.progressive.service.impl.MatchServiceImplJpa;
import com.edutech.progressive.service.impl.TeamServiceImplJpa;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class DayTwelveTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    CricketerRepository cricketerRepository;
    @Autowired
    MatchRepository matchRepository;
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    VoteRepository voteRepository;
    @Autowired
    TicketBookingRepository ticketBookingRepository;

    @InjectMocks
    private JwtUtil jwtUtil;
    @Mock
    private UserRepository userRepository;
    @Mock
    PasswordEncoder passwordEncoder;
    @Autowired
    ObjectMapper objectMapper;

    private final String secret = "secretKey000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";

    @Autowired
    WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp() throws SQLException {
        objectMapper = new ObjectMapper();
        voteRepository.deleteAll();
        ticketBookingRepository.deleteAll();
        matchRepository.deleteAll();
        cricketerRepository.deleteAll();
        teamRepository.deleteAll();
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        String url = "jdbc:mysql://localhost:3306/mydb";
        String user = "root";
        String password = "root";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {
            String deleteQuery = "DELETE FROM user";
            statement.executeUpdate(deleteQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    @Test
    void testGenerateToken_Day12() {
        User user = new User();
        user.setUsername("testUser");
        user.setRole("USER");

        when(userRepository.findByUsername("testUser")).thenReturn(user);

        String token = jwtUtil.generateToken("testUser");

        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

        assertEquals("testUser", claims.getSubject());
        assertEquals("USER", claims.get("role"));
    }

    @Test
    void testValidateToken_Day12() {
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn("testUser");

        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("sub", "testUser");

        String token = Jwts.builder()
                .setClaims(claimsMap)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, secret)
                .compact();

        boolean isValid = jwtUtil.validateToken(token, userDetails);

        assertTrue(isValid);
    }
 //Day13 Testcases
    // @Test
    // @WithMockUser(authorities = {"ADMIN", "USER"})
    // public void testTeamControllerGetAllTeam_Day13() throws Exception {
    //     Team team1 = getTeamObject(null, "Mumbai Indians");
    //     Team team2 = getTeamObject(null, "CSK");
    //     teamRepository.save(team1);
    //     teamRepository.save(team2);

    //     mockMvc.perform(get("/team"))
    //             .andExpect(status().isOk())
    //             .andExpect(content().contentType(MediaType.APPLICATION_JSON))
    //             .andExpect(jsonPath("$", hasSize(2)));
    // }


    // @Test
    // @WithMockUser(authorities = {"ADMIN"})
    // public void testTeamControllerAddTeam_Day13() throws Exception {
    //     Team team1 = getTeamObject(null, "Mumbai Indians");
    //     mockMvc.perform(post("/team")
    //                     .contentType(MediaType.APPLICATION_JSON)
    //                     .content(objectMapper.writeValueAsString(team1)))
    //             .andExpect(status().isCreated());

    //     List<Team> teams = teamRepository.findAll();
    //     assertEquals(1, teams.size());
    //     assertEquals("Mumbai Indians", teams.get(0).getTeamName());
    // }

    // @Test
    // @WithMockUser(authorities = {"ADMIN"})
    // public void testTeamControllerDeleteTeam_Day13() throws Exception {
    //     Team team1 = getTeamObject(null, "Mumbai Indians");
    //     int id = teamRepository.save(team1).getTeamId();

    //     mockMvc.perform(delete("/team/{teamId}", id)
    //                     .contentType(MediaType.APPLICATION_JSON))
    //             .andExpect(status().isNoContent());

    //     assertFalse(teamRepository.existsById(id));
    // }

    // private void assertFalse(boolean b) {
    // }

    // @Test
    // @WithMockUser(authorities = {"ADMIN", "USER"})
    // public void testCricketerControllerGetAllCricketer_Day13() throws Exception {
    //     CricketerService cricketerService = new CricketerServiceImplJpa(cricketerRepository);
    //     TeamService teamService = new TeamServiceImplJpa(teamRepository);
    //     Team team1 = getTeamObject(null, "Mumbai Indians");
    //     int id = teamService.addTeam(team1);
    //     team1.setTeamId(id);
    //     Cricketer cricketers1 = getCricketerObject(null, team1, "Dhoni",  20);
    //     Cricketer cricketers2 = getCricketerObject(null, team1, "Virat", 15);
    //     Cricketer cricketers3 = getCricketerObject(null, team1, "Warner", 14);
    //     cricketerService.addCricketer(cricketers1);
    //     cricketerService.addCricketer(cricketers2);
    //     cricketerService.addCricketer(cricketers3);

    //     mockMvc.perform(get("/cricketer"))
    //             .andExpect(status().isOk())
    //             .andExpect(content().contentType(MediaType.APPLICATION_JSON))
    //             .andExpect(jsonPath("$", hasSize(3)));
    // }

    // @Test
    // @WithMockUser(authorities = {"ADMIN"})
    // public void testCricketerControllerAddCricketer_Day13() throws Exception {
    //     CricketerService cricketerService = new CricketerServiceImplJpa(cricketerRepository);
    //     TeamService teamService = new TeamServiceImplJpa(teamRepository);
    //     Team team1 = getTeamObject(null, "Mumbai Indians");
    //     int id = teamService.addTeam(team1);
    //     team1.setTeamId(id);
    //     Cricketer cricketers1 = getCricketerObject(null, team1, "Dhoni",  20);


    //     mockMvc.perform(post("/cricketer")
    //                     .contentType(MediaType.APPLICATION_JSON)
    //                     .content(objectMapper.writeValueAsString(cricketers1)))
    //             .andExpect(status().isCreated());

    //     List<Cricketer> cricketerList = cricketerRepository.findAll();
    //     assertEquals(1, cricketerList.size());
    //     assertEquals("Dhoni", cricketerList.get(0).getCricketerName());
    // }

    // @Test
    // @WithMockUser(authorities = {"ADMIN", "USER"})
    // public void testMatchControllerGetAllMatches_Day13() throws Exception {
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

    //     mockMvc.perform(get("/match"))
    //             .andExpect(status().isOk())
    //             .andExpect(content().contentType(MediaType.APPLICATION_JSON))
    //             .andExpect(jsonPath("$", hasSize(3)));
    // }

    // @Test
    // @WithMockUser(authorities = {"ADMIN"})
    // public void testMatchControllerUpdateMatch_Day13() throws Exception {
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

    //     mockMvc.perform(put("/match/" + match1.getMatchId())
    //                     .contentType(MediaType.APPLICATION_JSON)
    //                     .content(objectMapper.writeValueAsString(match1)))
    //             .andExpect(status().isOk());

    //     Match updatedMatch = matchRepository.findByMatchId(match1.getMatchId());
    //     assertEquals("Completed", updatedMatch.getStatus());
    // }

    // @Test
    // void testRegisterUserSuccess_Day13() throws Exception {
    //     User mockUser = new User();
    //     mockUser.setUsername("testUser");
    //     mockUser.setPassword("Password@123");
    //     mockUser.setEmail("test@gmail.com");
    //     mockUser.setRole("USER");
    //     mockUser.setFullName("test user");

    //     mockMvc.perform(post("/user/register")
    //                     .contentType(MediaType.APPLICATION_JSON)
    //                     .content(objectMapper.writeValueAsString(mockUser)))
    //             .andExpect(status().isOk())
    //             .andExpect(jsonPath("$.username").value("testUser"));

    // }
}
