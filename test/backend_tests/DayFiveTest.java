package com.edutech.progressive;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.edutech.progressive.controller.TeamController;
import com.edutech.progressive.entity.Team;
import com.edutech.progressive.service.impl.TeamServiceImplArraylist;
import com.edutech.progressive.service.impl.TeamServiceImplJpa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
public class DayFiveTest {
    @Mock
    private TeamServiceImplJpa teamServiceImplJpa;

    @Mock
    private TeamServiceImplArraylist teamServiceImplArraylist;

    @InjectMocks
    private TeamController teamController;

    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(teamController).build();
        objectMapper = new ObjectMapper();
        String url = "jdbc:mysql://localhost:3306/mydb";
        String user = "root";
        String password = "root";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {
            List<String> tableNames = List.of("team", "cricketer", "matches");
            for (String tableName : tableNames) {
                String deleteQuery = "DELETE FROM " + tableName;
                statement.executeUpdate(deleteQuery);
            }
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

    // // Day - 4
    // @Test
    // public void testMainMethodOutput_Day4() {
    //     SpringApplication app = new SpringApplication(IplApplication.class);
    //     app.setDefaultProperties(Collections.singletonMap("server.port", "9999"));
    //     ConfigurableApplicationContext context = app.run();
    //     assertNotNull(context);
    //     context.close();
    // }

    // Day - 5 : Test cases for ArrayList Service methods
    @Test
    void testAddTeamToArrayList_Day5() throws Exception {
        Team team = getTeamObject(1, "Mumbai Indians");
        given(teamServiceImplArraylist.addTeam(team)).willReturn(1);

        mockMvc.perform(post("/team/toArrayList")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(team)))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetAllTeamsSortedByNameFromArrayList_Day5() throws Exception {
        TeamServiceImplArraylist teamServiceImplArray = new TeamServiceImplArraylist();
        teamServiceImplArray.emptyArrayList();
        Team team1 = getTeamObject(1, "Mumbai Indians");
        Team team2 = getTeamObject(2, "CDK");
        Team team3 = getTeamObject(3, "KKR");
        teamServiceImplArray.addTeam(team1);
        teamServiceImplArray.addTeam(team2);
        teamServiceImplArray.addTeam(team3);

        List<Team> result = teamServiceImplArray.getAllTeamsSortedByName();

        given(teamServiceImplArraylist.getAllTeamsSortedByName()).willReturn(result);
        mockMvc.perform(get("/team/fromArrayList/sorted"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].teamName").value(team2.getTeamName()));
    }

    @Test
    void testGetAllTeam_Day5() throws Exception {
        TeamServiceImplArraylist teamServiceImplArray = new TeamServiceImplArraylist();
        teamServiceImplArray.emptyArrayList();
        Team team1 = getTeamObject(1, "Mumbai Indians");
        Team team2 = getTeamObject(2, "CDK");
        Team team3 = getTeamObject(3, "KKR");
        teamServiceImplArray.addTeam(team1);
        teamServiceImplArray.addTeam(team2);
        teamServiceImplArray.addTeam(team3);

        List<Team> result = teamServiceImplArray.getAllTeams();
        given(teamServiceImplArraylist.getAllTeams()).willReturn(result);

        mockMvc.perform(get("/team/fromArrayList"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(result.size()));
    }

}



