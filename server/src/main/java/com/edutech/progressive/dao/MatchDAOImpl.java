package com.edutech.progressive.dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.entity.Match;
public class MatchDAOImpl implements MatchDAO {

    @Override
    public int addMatch(Match m) throws SQLException {
        final String sql = "INSERT INTO matches (first_team_id, second_team_id, match_date, venue, result, status, winner_team_id) " +
                           "VALUES (?,?,?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet keys = null;
        try {
            conn = DatabaseConnectionManager.getConnection();
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, m.getFirstTeamId());
            ps.setInt(2, m.getSecondTeamId());
            ps.setDate(3, new java.sql.Date(m.getMatchDate().getTime()));
            ps.setString(4, m.getVenue());
            ps.setString(5, m.getResult());
            ps.setString(6, m.getStatus()); // "Pending" | "Scheduled" | "Completed"
            if (m.getWinnerTeamId() > 0) {
                ps.setInt(7, m.getWinnerTeamId());
            } else {
                ps.setNull(7, Types.INTEGER);
            }

            int affected = ps.executeUpdate();
            if (affected == 0) throw new SQLException("Creating match failed, no rows affected.");

            keys = ps.getGeneratedKeys();
            if (keys.next()) {
                int id = keys.getInt(1);
                m.setMatchId(id);
                return id;
            }
            throw new SQLException("Creating match failed, no ID obtained.");
        } finally {
            if (keys != null) try { keys.close(); } catch (Exception ignored) {}
            if (ps != null)   try { ps.close(); }   catch (Exception ignored) {}
            if (conn != null) try { conn.close(); } catch (Exception ignored) {}
        }
    }

    @Override
    public Match getMatchById(int matchId) throws SQLException {
        final String sql = "SELECT match_id, first_team_id, second_team_id, match_date, venue, result, status, winner_team_id " +
                           "FROM matches WHERE match_id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DatabaseConnectionManager.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, matchId);
            rs = ps.executeQuery();

            if (rs.next()) {
                Match m = new Match();
                m.setMatchId(rs.getInt("match_id"));
                m.setFirstTeamId(rs.getInt("first_team_id"));
                m.setSecondTeamId(rs.getInt("second_team_id"));

                java.sql.Date d = rs.getDate("match_date");
                if (d != null) m.setMatchDate(new java.util.Date(d.getTime()));

                m.setVenue(rs.getString("venue"));
                m.setResult(rs.getString("result"));
                m.setStatus(rs.getString("status"));

                int winnerId = rs.getInt("winner_team_id");
                if (!rs.wasNull()) {
                    m.setWinnerTeamId(winnerId);  // if null, leave default (0)
                }
                return m;
            }
            return null;
        } finally {
            if (rs != null)   try { rs.close(); }   catch (Exception ignored) {}
            if (ps != null)   try { ps.close(); }   catch (Exception ignored) {}
            if (conn != null) try { conn.close(); } catch (Exception ignored) {}
        }
    }

    @Override
    public void updateMatch(Match m) throws SQLException {
        final String sql = "UPDATE matches SET first_team_id=?, second_team_id=?, match_date=?, venue=?, result=?, status=?, winner_team_id=? " +
                           "WHERE match_id=?";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DatabaseConnectionManager.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, m.getFirstTeamId());
            ps.setInt(2, m.getSecondTeamId());
            ps.setDate(3, new java.sql.Date(m.getMatchDate().getTime()));
            ps.setString(4, m.getVenue());
            ps.setString(5, m.getResult());
            ps.setString(6, m.getStatus());

            if (m.getWinnerTeamId() > 0) {
                ps.setInt(7, m.getWinnerTeamId());
            } else {
                ps.setNull(7, Types.INTEGER); 
            }

            ps.setInt(8, m.getMatchId());
            int affected = ps.executeUpdate();

           
        } finally {
            if (ps != null)   try { ps.close(); }   catch (Exception ignored) {}
            if (conn != null) try { conn.close(); } catch (Exception ignored) {}
        }
    }

    @Override
    public void deleteMatch(int matchId) throws SQLException {
        final String sql = "DELETE FROM matches WHERE match_id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DatabaseConnectionManager.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, matchId);
            ps.executeUpdate();
        } finally {
            if (ps != null)   try { ps.close(); }   catch (Exception ignored) {}
            if (conn != null) try { conn.close(); } catch (Exception ignored) {}
        }
    }

    @Override
    public List<Match> getAllMatches() throws SQLException {
        final String sql = "SELECT match_id, first_team_id, second_team_id, match_date, venue, result, status, winner_team_id FROM matches";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Match> list = new ArrayList<>();
        try {
            conn = DatabaseConnectionManager.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Match m = new Match();
                m.setMatchId(rs.getInt("match_id"));
                m.setFirstTeamId(rs.getInt("first_team_id"));
                m.setSecondTeamId(rs.getInt("second_team_id"));

                java.sql.Date d = rs.getDate("match_date");
                if (d != null) m.setMatchDate(new java.util.Date(d.getTime()));

                m.setVenue(rs.getString("venue"));
                m.setResult(rs.getString("result"));
                m.setStatus(rs.getString("status"));

                int winnerId = rs.getInt("winner_team_id");
                if (!rs.wasNull()) m.setWinnerTeamId(winnerId);

                list.add(m);
            }
            return list;
        } 
        catch(Exception e)
        {
          e.printStackTrace();
        }
        return null;
    }
}

   