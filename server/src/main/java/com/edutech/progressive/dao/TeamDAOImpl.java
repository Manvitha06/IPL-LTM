package com.edutech.progressive.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.entity.Team;

public class TeamDAOImpl implements TeamDAO {

    private Connection connection;
     

    public TeamDAOImpl() {
        this.connection= DatabaseConnectionManager.getConnection();
    }

    @Override
    public int addTeam(Team team) {
        String query="insert into team(team_name,location,owner_name,establishment_year) values(?,?,?,?)";
        try {
            PreparedStatement ps= connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, team.getTeamName());
            ps.setString(2, team.getLocation());
            ps.setString(3, team.getOwnerName());
            ps.setInt(4, team.getEstablishmentYear());
            int c= ps.executeUpdate();
            if(c>0){
                ResultSet rs= ps.getGeneratedKeys();
                rs.next();
                team.setTeamId(rs.getInt(1));
                return team.getTeamId();
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Team getTeamById(int teamId) {
        String query="select *from team where team_id=?";
        try {
            PreparedStatement ps= connection.prepareStatement(query);
            ps.setInt(1, teamId);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                Team t1= new Team(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
                return t1;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateTeam(Team team) {
      String query ="update team set team_name=?,location=?,owner_name=?,establishment_year=? where team_id=?";
      try {
        PreparedStatement ps= connection.prepareStatement(query);
             ps.setString(1, team.getTeamName());
            ps.setString(2, team.getLocation());
            ps.setString(3, team.getOwnerName());
            ps.setInt(4, team.getEstablishmentYear());
            ps.setInt(5, team.getTeamId());
            ps.executeUpdate();
    } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    }

    @Override
    public void deleteTeam(int teamId) {
      String query= "delete from team where team_id=?";
      try {
        PreparedStatement ps= connection.prepareStatement(query);
        ps.setInt(1, teamId);
        ps.executeUpdate();
    } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } 
    }

    @Override
    public List<Team> getAllTeams() {
        List<Team> teams= new ArrayList<>();
        String query="select *from team";
        try {
            PreparedStatement ps= connection.prepareStatement(query);
            ResultSet rs= ps.executeQuery();
            while (rs.next()) {
                Team t1= new Team(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
                teams.add(t1);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return teams;
    }



}