package com.edutech.progressive;

import com.edutech.progressive.entity.Team;
import com.edutech.progressive.exception.NoMatchesFoundException;
import com.edutech.progressive.exception.TeamAlreadyExistsException;
import com.edutech.progressive.exception.TeamDoesNotExistException;
import com.edutech.progressive.repository.TeamRepository;
import com.edutech.progressive.repository.MatchRepository;
import com.edutech.progressive.service.TeamService;
import com.edutech.progressive.service.MatchService;
import com.edutech.progressive.service.impl.TeamServiceImplJpa;
import com.edutech.progressive.service.impl.MatchServiceImplJpa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = IplApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class DayNineTest {

    @Autowired
    TeamRepository teamRepository;
    @Autowired
    MatchRepository matchRepository;

    @BeforeEach
    public void setUp() throws SQLException {
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

    @Test
    public void testTeamAlreadyExistsException_Day9() throws SQLException {
        TeamService teamService = new TeamServiceImplJpa(teamRepository);
        Team team = getTeamObject(null, "Mumbai Indians");
        teamRepository.save(team);

        assertThrows(TeamAlreadyExistsException.class, () -> {
            teamService.addTeam(team);
        });
    }

    @Test
    public void testTeamDoesNotExistException_Day9() throws SQLException {
        TeamService teamService = new TeamServiceImplJpa(teamRepository);
        Team team = getTeamObject(null, "Mumbai Indians");
        teamRepository.save(team);

        assertThrows(TeamDoesNotExistException.class, () -> {
            teamService.getTeamById(1);
        });
    }

    @Test
    public void testNoMatchesFoundExceptionException_Day9() throws SQLException {
        MatchService matchService = new MatchServiceImplJpa(matchRepository);

        assertThrows(NoMatchesFoundException.class, () -> {
            matchService.getAllMatchesByStatus("Scheduled");
        });
    }

}
