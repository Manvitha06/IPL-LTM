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

 
 
    @Override
    public int addTeam(Team t) throws SQLException {
        String sql = "INSERT INTO team (team_name, location, owner_name, establishment_year) VALUES (?,?,?,?)";
        Connection conn = null;
        PreparedStatement ps = null;
      
        try {
            conn = DatabaseConnectionManager.getConnection();
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, t.getTeamName());
            ps.setString(2, t.getLocation());
            ps.setString(3, t.getOwnerName());
            ps.setInt(4, t.getEstablishmentYear());
            ps.executeUpdate();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next())
            {int i=rs.getInt(1);
              t.setTeamId(i);
              return i;
            }
          
        } catch (SQLException e) {
            throw e;
        } 
        return 0;
    }

    @Override
    public Team getTeamById(int teamId) throws SQLException {
        String sql = "SELECT team_id, team_name, location, owner_name, establishment_year FROM team WHERE team_id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Team t = null;
        try {
            conn = DatabaseConnectionManager.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, teamId);
            rs = ps.executeQuery();
            if (rs.next()) {
                t = new Team();
                t.setTeamId(rs.getInt("team_id"));
                t.setTeamName(rs.getString("team_name"));
                t.setLocation(rs.getString("location"));
                t.setOwnerName(rs.getString("owner_name"));
                t.setEstablishmentYear(rs.getInt("establishment_year"));
            }
            return t;
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) try { rs.close(); } catch (Exception ignored) {}
            if (ps != null) try { ps.close(); } catch (Exception ignored) {}
            if (conn != null) try { conn.close(); } catch (Exception ignored) {}
        }
    }

    @Override
    public void updateTeam(Team t) throws SQLException {
        String sql = "UPDATE team SET team_name=?, location=?, owner_name=?, establishment_year=? WHERE team_id=?";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DatabaseConnectionManager.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, t.getTeamName());
            ps.setString(2, t.getLocation());
            ps.setString(3, t.getOwnerName());
            ps.setInt(4, t.getEstablishmentYear());
            ps.setInt(5, t.getTeamId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } 
    }

    @Override
    public void deleteTeam(int teamId) throws SQLException {
        String sql = "DELETE FROM team WHERE team_id=?";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DatabaseConnectionManager.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, teamId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } 
    }

    @Override
    public List<Team> getAllTeams() throws SQLException {
        String sql = "SELECT team_id, team_name, location, owner_name, establishment_year FROM team";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Team> list = new ArrayList<>();
        try {
            conn = DatabaseConnectionManager.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Team t = new Team();
                t.setTeamId(rs.getInt("team_id"));
                t.setTeamName(rs.getString("team_name"));
                t.setLocation(rs.getString("location"));
                t.setOwnerName(rs.getString("owner_name"));
                t.setEstablishmentYear(rs.getInt("establishment_year"));
                list.add(t);
            }
            return list;
        } catch (SQLException e) {
            throw e;
        } 
    }
}