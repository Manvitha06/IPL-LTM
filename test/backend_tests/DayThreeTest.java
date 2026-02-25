package com.edutech.progressive;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.dao.CricketerDAOImpl;
import com.edutech.progressive.dao.MatchDAOImpl;
import com.edutech.progressive.dao.TeamDAOImpl;
import com.edutech.progressive.entity.Cricketer;
import com.edutech.progressive.entity.Match;
import com.edutech.progressive.entity.Team;
import com.edutech.progressive.service.CricketerService;
import com.edutech.progressive.service.MatchService;
import com.edutech.progressive.service.TeamService;
import com.edutech.progressive.service.impl.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DayThreeTest {
  private TeamService teamService;
  private TeamServiceImplArraylist teamServiceImplArraylist;
  private CricketerService cricketerService;
  private CricketerServiceImplArraylist cricketerServiceImplArraylist;

  @BeforeEach
  public void setUp() {
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

  Cricketer getCricketerObject(Integer id, Integer teamId, String cricketerName, int experience) {
      Cricketer cricketer = new Cricketer();
      if (id != null) {
          cricketer.setCricketerId(id.intValue());
      }
      cricketer.setTeamId(teamId);
      cricketer.setCricketerName(cricketerName);
      cricketer.setExperience(experience);
      return cricketer;
  }

  Match getMatchObject(Integer id, int team1, int team2) {
      Match match = new Match();
      if ( id != null) {
          match.setMatchId(id);
      }
      match.setFirstTeamId(team1);
      match.setSecondTeamId(team2);
      match.setMatchDate(new java.util.Date());
      return match;
  }

//   @Test
//   public void testAddTeamToArrayList_Day2() throws SQLException {
//       teamServiceImplArraylist = new TeamServiceImplArraylist();
//       teamServiceImplArraylist.emptyArrayList();
//       Team team = getTeamObject(1, "Mumbai Indians");
//       int result = teamServiceImplArraylist.addTeam(team);
//       assertNotNull(result);
//       assertEquals(result, 1);
//   }

//   @Test
//   public void testGetAllTeamsSortedByNameFromArrayList_Day2() throws SQLException {
//       teamServiceImplArraylist = new TeamServiceImplArraylist();
//       teamServiceImplArraylist.emptyArrayList();
//       Team team1 = getTeamObject(1, "Mumbai Indians");
//       Team team2 = getTeamObject(2, "CSK");
//       Team team3 = getTeamObject(3, "KKR");

//       teamServiceImplArraylist.addTeam(team1);
//       teamServiceImplArraylist.addTeam(team2);
//       teamServiceImplArraylist.addTeam(team3);

//       List<Team> result = teamServiceImplArraylist.getAllTeamsSortedByName();
//       assertNotNull(result);
//       assertFalse(result.isEmpty());

//       assertTrue(result.get(0).getTeamName().compareTo(result.get(1).getTeamName()) <= 0);
//       assertTrue(result.get(1).getTeamName().compareTo(result.get(2).getTeamName()) <= 0);
//   }

//   @Test
//   public void testGetAllTeams_Day2() throws SQLException {
//       teamServiceImplArraylist = new TeamServiceImplArraylist();
//       teamServiceImplArraylist.emptyArrayList();
//       Team team1 = getTeamObject(1, "Mumbai Indians");
//       Team team2 = getTeamObject(2, "CSK");
//       Team team3 = getTeamObject(3, "KKR");
//       teamServiceImplArraylist.addTeam(team1);
//       teamServiceImplArraylist.addTeam(team2);
//       teamServiceImplArraylist.addTeam(team3);
//       List<Team> result = teamServiceImplArraylist.getAllTeams();
//       assertNotNull(result);
//       assertEquals(3, result.size());
//   }

//   @Test
//   public void testGetAllCricketersSortedByExperience_Day2() throws SQLException {
//       cricketerServiceImplArraylist = new CricketerServiceImplArraylist();
//       cricketerServiceImplArraylist.emptyArrayList();
//       Cricketer cricketer1 = getCricketerObject(1, 1, "Dhoni", 15);
//       Cricketer cricketer2 = getCricketerObject(2, 2, "Smith", 14);
//       Cricketer cricketer3 = getCricketerObject(3, 3, "Kohli", 10);

//       cricketerServiceImplArraylist.addCricketer(cricketer1);
//       cricketerServiceImplArraylist.addCricketer(cricketer2);
//       cricketerServiceImplArraylist.addCricketer(cricketer3);
//       List<Cricketer> result = cricketerServiceImplArraylist.getAllCricketersSortedByExperience();

//       assertNotNull(result);
//       assertFalse(result.isEmpty());

//       // Check if the list is sorted by experience
//       assertTrue(result.get(0).getExperience() == cricketer3.getExperience());
//       assertTrue(result.get(1).getExperience() <= cricketer1.getExperience());
//   }

//   @Test
//   public void testAddCricketerToArrayList_Day2() throws SQLException {
//       cricketerServiceImplArraylist = new CricketerServiceImplArraylist();
//       cricketerServiceImplArraylist.emptyArrayList();
//       Cricketer cricketer1 = getCricketerObject(1, 1, "Dhoni", 15);
//       int result = cricketerServiceImplArraylist.addCricketer(cricketer1);
//       assertNotNull(result);
//       assertEquals(1, result);
//   }

  private void insertSampleTeam() throws SQLException {
      insertSampleTeam("CSK", "Chennai", "Dhoni", 2014);
      insertSampleTeam("Mumbai Indians", "Mumbai", "Ambani", 2014);
  }

  private int insertSampleTeam(String teamName, String location, String ownerName, int establishment) throws SQLException {
      try (Connection connection = DatabaseConnectionManager.getConnection();
           PreparedStatement statement = connection.prepareStatement(
                   "INSERT INTO team (team_name, location, owner_name, establishment_year) VALUES (?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
          statement.setString(1, teamName);
          statement.setString(2, location);
          statement.setString(3, ownerName);
          statement.setInt(4, establishment);
          statement.executeUpdate();

          int generatedID = -1;
          try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
              if (generatedKeys.next()) {
                  generatedID = generatedKeys.getInt(1);
              }
          }
          return generatedID;
      }
  }

  @Test
  public void testGetAllTeams_Day3() throws SQLException {
      teamService = new TeamServiceImplJdbc(new TeamDAOImpl());
      // Insert sample team into the test database
      insertSampleTeam();

      List<Team> team = teamService.getAllTeams();

      assertNotNull(team);
      assertEquals(2, team.size()); // Adjust as per your test data
  }


  @Test
  public void testAddTeam_Day3() throws SQLException {
      teamService = new TeamServiceImplJdbc(new TeamDAOImpl());

      Team newTeam = getTeamObject(null, "Mumbai Indians");

      int id = teamService.addTeam(newTeam);

      Team team = teamService.getTeamById(id);
      assertNotNull(team);
      assertEquals(newTeam.getTeamId(), team.getTeamId());
      assertEquals(newTeam.getTeamName(), team.getTeamName());
  }

  @Test
  public void testUpdateTeam_Day3() throws SQLException {
      teamService = new TeamServiceImplJdbc(new TeamDAOImpl());

      // Insert a sample team into the test database
      int teamId = insertSampleTeam("CSK", "Chennai", "Dhoni", 2014);

      Team updatedTeams = getTeamObject(teamId, "Chennai Super Kings");

      teamService.updateTeam(updatedTeams);

      Team retrievedTeam = teamService.getTeamById(teamId);

      assertNotNull(retrievedTeam);
      assertEquals(updatedTeams.getTeamName(), retrievedTeam.getTeamName());
  }

  @Test
  public void testDeleteTeam_Day3() throws SQLException {
      teamService = new TeamServiceImplJdbc(new TeamDAOImpl());
      // Insert a sample team into the test database
      int teamId = insertSampleTeam("CSK", "Chennai", "Dhoni", 2014);
      Team savedTeam = teamService.getTeamById(teamId);
      assertNotNull(savedTeam);

      teamService.deleteTeam(teamId);

      Team deletedTeam = teamService.getTeamById(teamId);

      assertNull(deletedTeam);
  }

  @Test
  public void testGetAllTeamsSortedByNameFromArrayList_Day3() throws SQLException {
      teamService = new TeamServiceImplJdbc(new TeamDAOImpl());
      // Insert sample team into the test database
      insertSampleTeam();

      List<Team> sortedTeam = teamService.getAllTeamsSortedByName();

      assertNotNull(sortedTeam);
      assertEquals(2, sortedTeam.size()); // Adjust as per your test data
      assertTrue(sortedTeam.get(0).getTeamName().compareTo(sortedTeam.get(1).getTeamName()) < 0);
  }

  @Test
  public void testAddCricketer_Day3() throws SQLException {
      teamService = new TeamServiceImplJdbc(new TeamDAOImpl());
      cricketerService = new CricketerServiceImplJdbc(new CricketerDAOImpl());

      Team team = getTeamObject(null, "CSK");
      int teamId = teamService.addTeam(team);
      Cricketer cricketersToAdd = getCricketerObject(null, teamId, "Dhoni", 15);
      int generatedAccountId = cricketerService.addCricketer(cricketersToAdd);

      Cricketer retrievedCricketer = cricketerService.getCricketerById(generatedAccountId);

      assertNotNull(retrievedCricketer);
      assertEquals(generatedAccountId, retrievedCricketer.getCricketerId());
      assertEquals(15, retrievedCricketer.getExperience());
  }

  @Test
  public void testUpdateCricketer_Day3() throws SQLException {
      teamService = new TeamServiceImplJdbc(new TeamDAOImpl());
      cricketerService = new CricketerServiceImplJdbc(new CricketerDAOImpl());

      Team team = getTeamObject(null, "CSK");
      int teamId = teamService.addTeam(team);
      Cricketer cricketer = getCricketerObject(null, teamId, "Dhoni", 15);

      int generatedCricketerId = cricketerService.addCricketer(cricketer);
      cricketer.setCricketerId(generatedCricketerId);
      cricketer.setExperience(20);
      cricketerService.updateCricketer(cricketer);

      Cricketer updatedCricketer = cricketerService.getCricketerById(generatedCricketerId);

      assertNotNull(updatedCricketer);
      assertEquals(generatedCricketerId, updatedCricketer.getCricketerId());
      assertEquals(20, updatedCricketer.getExperience());
  }

  @Test
  public void testDeleteCricketer_Day3() throws SQLException {
      teamService = new TeamServiceImplJdbc(new TeamDAOImpl());
      cricketerService = new CricketerServiceImplJdbc(new CricketerDAOImpl());

      Team team = getTeamObject(null, "CSK");
      int teamId = teamService.addTeam(team);

      Cricketer cricketersToAdd = getCricketerObject(null, teamId, "Dhoni", 15);
      int generatedCricketerId = cricketerService.addCricketer(cricketersToAdd);

      cricketerService.deleteCricketer(generatedCricketerId);

      assertNull(cricketerService.getCricketerById(generatedCricketerId));
  }

  @Test
  public void testGetAllCricketers_Day3() throws SQLException {
      teamService = new TeamServiceImplJdbc(new TeamDAOImpl());
      cricketerService = new CricketerServiceImplJdbc(new CricketerDAOImpl());

      Team team = getTeamObject(null, "CSK");
      int teamId = teamService.addTeam(team);

      Cricketer cricketers1 = getCricketerObject(null, teamId, "Dhoni",  20);
      Cricketer cricketers2 = getCricketerObject(null, teamId, "Virat", 15);
      Cricketer cricketers3 = getCricketerObject(null, teamId, "Warner", 14);
      cricketerService.addCricketer(cricketers1);
      cricketerService.addCricketer(cricketers2);
      cricketerService.addCricketer(cricketers3);

      List<Cricketer> allCricketer = cricketerService.getAllCricketers();

      assertNotNull(allCricketer);
      assertTrue(allCricketer.size() == 3);
  }

  @Test
  public void testGetAllCricketersSortedByExperience_Day3() throws SQLException {
      teamService = new TeamServiceImplJdbc(new TeamDAOImpl());
      cricketerService = new CricketerServiceImplJdbc(new CricketerDAOImpl());

      Team team = getTeamObject(null, "CSK");
      int teamId = teamService.addTeam(team);
      List<Cricketer> unsortedCricketer = new ArrayList<>();
      unsortedCricketer.add(getCricketerObject(null, teamId, "Dhoni",  20));
      unsortedCricketer.add(getCricketerObject(null, teamId, "Virat", 15));
      unsortedCricketer.add(getCricketerObject(null, teamId, "Warner", 14));

      for (Cricketer cricketer : unsortedCricketer) {
          cricketerService.addCricketer(cricketer);
      }

      List<Cricketer> sortedCricketers = cricketerService.getAllCricketersSortedByExperience();

      assertNotNull(sortedCricketers);
      assertEquals(sortedCricketers.get(0).getExperience(), 14);
      assertEquals(sortedCricketers.get(1).getExperience(), 15);
      assertEquals(sortedCricketers.get(2).getExperience(), 20);
  }

  @Test
  public void testGetAllMatches_Day3() throws SQLException {
      teamService = new TeamServiceImplJdbc(new TeamDAOImpl());
      MatchService matchService = new MatchServiceImplJdbc(new MatchDAOImpl());

      Team team1 = getTeamObject(null, "CSK");
      int teamId1 = teamService.addTeam(team1);

      Team team2 = getTeamObject(null, "CSK");
      int teamId2 = teamService.addTeam(team2);

      List<Match> matches = new ArrayList<>();
      matches.add(getMatchObject(null, teamId1, teamId2));
      matches.add(getMatchObject(null, teamId1, teamId2));
      matches.add(getMatchObject(null, teamId1, teamId2));

      for (Match match : matches) {
          matchService.addMatch(match);
      }

      List<Match> retrievedMatches = matchService.getAllMatches();
      assertNotNull(retrievedMatches);
      assertEquals(retrievedMatches.size(), 3);
  }

  @Test
  public void testGetMatchById_Day3() throws SQLException {
      teamService = new TeamServiceImplJdbc(new TeamDAOImpl());
      MatchService matchService = new MatchServiceImplJdbc(new MatchDAOImpl());

      Team team1 = getTeamObject(null, "CSK");
      int teamId1 = teamService.addTeam(team1);

      Team team2 = getTeamObject(null, "CSK");
      int teamId2 = teamService.addTeam(team2);

      Match match = getMatchObject(null, teamId1, teamId2);
      int matchId = matchService.addMatch(match);

      Match retrievedMatch = matchService.getMatchById(matchId);

      assertNotNull(retrievedMatch);
      assertEquals(match.getFirstTeamId(), retrievedMatch.getFirstTeamId());
      assertEquals(match.getSecondTeamId(), retrievedMatch.getSecondTeamId());
  }

  @Test
  public void testUpdateMatch_Day3() throws SQLException {
      teamService = new TeamServiceImplJdbc(new TeamDAOImpl());
      MatchService matchService = new MatchServiceImplJdbc(new MatchDAOImpl());

      Team team1 = getTeamObject(null, "CSK");
      int teamId1 = teamService.addTeam(team1);

      Team team2 = getTeamObject(null, "CSK");
      int teamId2 = teamService.addTeam(team2);

      Match match = getMatchObject(null, teamId1, teamId2);
      int matchId = matchService.addMatch(match);

      match.setWinnerTeamId(match.getFirstTeamId());
      matchService.updateMatch(match);

      Match updatedMatch = matchService.getMatchById(matchId);
      assertNotNull(updatedMatch);
      assertEquals(match.getWinnerTeamId(), updatedMatch.getWinnerTeamId());
  }

  @Test
  public void testDeleteMatch_Day3() throws SQLException {
      teamService = new TeamServiceImplJdbc(new TeamDAOImpl());
      cricketerService = new CricketerServiceImplJdbc(new CricketerDAOImpl());
      MatchService matchService = new MatchServiceImplJdbc(new MatchDAOImpl());

      Team team1 = getTeamObject(null, "CSK");
      int teamId1 = teamService.addTeam(team1);

      Team team2 = getTeamObject(null, "CSK");
      int teamId2 = teamService.addTeam(team2);

      Match match = getMatchObject(null, teamId1, teamId2);
      int matchId = matchService.addMatch(match);

      assertNotNull(matchId);

      assertNotEquals(-1, matchId);

      matchService.deleteMatch(matchId);

      assertNull(matchService.getMatchById(matchId));
  }
}
