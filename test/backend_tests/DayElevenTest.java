package com.edutech.progressive;

import com.edutech.progressive.entity.*;
import com.edutech.progressive.repository.*;
import com.edutech.progressive.service.MatchService;
import com.edutech.progressive.service.TeamService;
import com.edutech.progressive.service.impl.MatchServiceImplJpa;
import com.edutech.progressive.service.impl.TeamServiceImplJpa;
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

import java.lang.reflect.Field;
import java.sql.SQLException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = IplApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class DayElevenTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    TicketBookingRepository ticketBookingRepository;

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    CricketerRepository cricketerRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() throws SQLException {
        objectMapper = new ObjectMapper();
        voteRepository.deleteAll();
        ticketBookingRepository.deleteAll();
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

    TicketBooking getTicketBooking(Integer id, Match match, String email, int numOfTickets) {
        TicketBooking ticketBooking = new TicketBooking();
        if ( id != null ) {
            ticketBooking.setBookingId(id);
        }
        ticketBooking.setEmail(email);
        ticketBooking.setMatch(match);
        ticketBooking.setNumberOfTickets(numOfTickets);
        return ticketBooking;
    }


    @Test
    @WithMockUser(authorities = {"ADMIN", "USER"})
    public void testGetAllBookings_Day11() throws Exception {
        MatchService matchService = new MatchServiceImplJpa(matchRepository);
        TeamService teamService = new TeamServiceImplJpa(teamRepository);
        Team team1 = getTeamObject(null, "Mumbai Indians");
        team1.setTeamId(teamService.addTeam(team1));
        Team team2 = getTeamObject(null, "CSK");
        team2.setTeamId(teamService.addTeam(team2));
        Match match1 = getMatchObject(null, team1, team2);
        match1.setMatchId(matchService.addMatch(match1));

        TicketBooking ticketBooking1 = getTicketBooking(null, match1, "user1@gmail.com", 5);
        TicketBooking ticketBooking2 = getTicketBooking(null, match1, "user2@gmail.com", 2);
        TicketBooking ticketBooking3 = getTicketBooking(null, match1, "user3@gmail.com", 15);
        ticketBookingRepository.save(ticketBooking1);
        ticketBookingRepository.save(ticketBooking2);
        ticketBookingRepository.save(ticketBooking3);

        mockMvc.perform(get("/ticket"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(3))
                .andDo(print());
    }

    @Test
    @WithMockUser(authorities = {"USER"})
    public void testCreateBooking_Day11() throws Exception {
        MatchService matchService = new MatchServiceImplJpa(matchRepository);
        TeamService teamService = new TeamServiceImplJpa(teamRepository);
        Team team1 = getTeamObject(null, "Mumbai Indians");
        team1.setTeamId(teamService.addTeam(team1));
        Team team2 = getTeamObject(null, "CSK");
        team2.setTeamId(teamService.addTeam(team2));
        Match match1 = getMatchObject(null, team1, team2);
        match1.setMatchId(matchService.addMatch(match1));

        TicketBooking ticketBooking1 = getTicketBooking(null, match1, "user1@gmail.com", 5);

        mockMvc.perform(post("/ticket")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ticketBooking1)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    @WithMockUser(authorities = {"USER"})
    public void testCancelBooking_Day11() throws Exception {
        MatchService matchService = new MatchServiceImplJpa(matchRepository);
        TeamService teamService = new TeamServiceImplJpa(teamRepository);
        Team team1 = getTeamObject(null, "Mumbai Indians");
        team1.setTeamId(teamService.addTeam(team1));
        Team team2 = getTeamObject(null, "CSK");
        team2.setTeamId(teamService.addTeam(team2));
        Match match1 = getMatchObject(null, team1, team2);
        match1.setMatchId(matchService.addMatch(match1));

        TicketBooking ticketBooking1 = getTicketBooking(null, match1, "user1@gmail.com", 5);
        int id = ticketBookingRepository.save(ticketBooking1).getBookingId();

        mockMvc.perform(delete("/ticket/" + id))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    @WithMockUser(authorities = {"USER"})
    public void testGetBookingsByUserEmail_Day11() throws Exception {
        MatchService matchService = new MatchServiceImplJpa(matchRepository);
        TeamService teamService = new TeamServiceImplJpa(teamRepository);
        Team team1 = getTeamObject(null, "Mumbai Indians");
        team1.setTeamId(teamService.addTeam(team1));
        Team team2 = getTeamObject(null, "CSK");
        team2.setTeamId(teamService.addTeam(team2));
        Match match1 = getMatchObject(null, team1, team2);
        match1.setMatchId(matchService.addMatch(match1));

        TicketBooking ticketBooking1 = getTicketBooking(null, match1, "user1@gmail.com", 5);
        ticketBookingRepository.save(ticketBooking1);

        mockMvc.perform(get("/ticket/user/user1@gmail.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email").value("user1@gmail.com"))
                .andDo(print());
    }
}
