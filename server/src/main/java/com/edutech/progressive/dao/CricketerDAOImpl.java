package com.edutech.progressive.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.entity.Cricketer;

public class CricketerDAOImpl implements CricketerDAO {
    private Connection connection;
    

    public CricketerDAOImpl() {
        this.connection = DatabaseConnectionManager.getConnection();
    }

    @Override
    public int addCricketer(Cricketer cricketer) {
        String query="insert into cricketer (team_id,cricketer_name,age,nationality,experience,role,total_runs,total_wickets) values(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps= connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, cricketer.getTeamId());
            ps.setString(2, cricketer.getCricketerName());
            ps.setInt(3, cricketer.getAge());
            ps.setString(4, cricketer.getNationality());
            ps.setInt(5,cricketer.getExperience());
            ps.setString(6, cricketer.getRole());
            ps.setInt(7, cricketer.getTotalRuns());
            ps.setInt(8, cricketer.getTotalWickets());
            int c= ps.executeUpdate();
            if(c>0){
                ResultSet rs= ps.getGeneratedKeys();
                rs.next();
                cricketer.setCricketerId(rs.getInt(1));
                return cricketer.getCricketerId();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Cricketer getCricketerById(int cricketerId) {
        String query= "select *from cricketer where cricketer_id=?";
        try {
            PreparedStatement ps= connection.prepareStatement(query);
            ps.setInt(1, cricketerId);
            ResultSet rs= ps.executeQuery();
            if(rs.next()){
                Cricketer c1= new Cricketer();
                c1.setCricketerId(rs.getInt(1));
                c1.setTeamId(rs.getInt(2));
                c1.setCricketerName(rs.getString(3));
                c1.setAge(rs.getInt(4));
                c1.setNationality(rs.getString(5));
                c1.setExperience(rs.getInt(6));
                c1.setRole(rs.getString(7));
                c1.setTotalRuns(rs.getInt(8));
                c1.setTotalWickets(rs.getInt(9));
                return c1;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateCricketer(Cricketer cricketer) {
        String query= "update cricketer set team_id=?,cricketer_name=?,age=?,nationality=?,experience=?,role=?,total_runs=?,total_wickets=? where cricketer_id=?";
        
        try {
            PreparedStatement ps= connection.prepareStatement(query);
            ps.setInt(1, cricketer.getTeamId());
            ps.setString(2, cricketer.getCricketerName());
            ps.setInt(3, cricketer.getAge());
            ps.setString(4, cricketer.getNationality());
            ps.setInt(5,cricketer.getExperience());
            ps.setString(6, cricketer.getRole());
            ps.setInt(7, cricketer.getTotalRuns());
            ps.setInt(8, cricketer.getTotalWickets());
            ps.setInt(9, cricketer.getCricketerId());
            ps.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCricketer(int cricketerId) {
        String query="delete from cricketer where cricketer_id=?";
        try {
            PreparedStatement ps= connection.prepareStatement(query);
            ps.setInt(1, cricketerId);
            ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public List<Cricketer> getAllCricketers() {
        List<Cricketer> cricketers= new ArrayList<>();
        String query= "select *from cricketer";
        try {
            PreparedStatement ps= connection.prepareStatement(query);

            ResultSet rs= ps.executeQuery();
            while (rs.next()) {
                Cricketer c1= new Cricketer();
                c1.setCricketerId(rs.getInt(1));
                c1.setTeamId(rs.getInt(2));
                c1.setCricketerName(rs.getString(3));
                c1.setAge(rs.getInt(4));
                c1.setNationality(rs.getString(5));
                c1.setExperience(rs.getInt(6));
                c1.setRole(rs.getString(7));
                c1.setTotalRuns(rs.getInt(8));
                c1.setTotalWickets(rs.getInt(9));
                cricketers.add(c1);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return cricketers;
    }
}