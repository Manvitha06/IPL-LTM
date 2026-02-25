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

public class CricketerDAOImpl implements CricketerDAO{
   Connection conn;
    public CricketerDAOImpl() {
      conn=DatabaseConnectionManager.getConnection();
   }

    @Override
    public int addCricketer(Cricketer cricketer)throws SQLException {
      String q="insert into cricketer (team_id, cricketer_name,age,nationality,experience,role,total_runs,total_wickets) values (?,?,?,?,?,?,?,?)";
      PreparedStatement ps=conn.prepareStatement(q,Statement.RETURN_GENERATED_KEYS);
     
 ps.setInt(1, cricketer.getTeamId());
            ps.setString(2, cricketer.getCricketerName());
            ps.setInt(3, cricketer.getAge());
            ps.setString(4, cricketer.getNationality());
            ps.setInt(5, cricketer.getExperience());
            ps.setString(6, cricketer.getRole());
            ps.setInt(7, cricketer.getTotalRuns());
            ps.setInt(8, cricketer.getTotalWickets());

      ps.executeUpdate();
      ResultSet rs=ps.getGeneratedKeys();
      if(rs.next())
      {int i=rs.getInt(1);
       cricketer.setCricketerId(i);
       return i;
      }
      return -1;

    }

    @Override
    public Cricketer getCricketerById(int cricketerId)throws SQLException {
      String q="select * from cricketer where cricketer_id=?";
      PreparedStatement ps=conn.prepareStatement(q);
      ps.setInt(1,cricketerId);
      ResultSet rs=ps.executeQuery();
      if(rs.next())
      {
         Cricketer c=new Cricketer(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getInt(6),rs.getString(7),rs.getInt(8),rs.getInt(9));
         return c;
      }
     return null;
    }

    @Override
    public void updateCricketer(Cricketer cricketer)throws SQLException{
     String q="UPDATE cricketer SET team_id = ?, cricketer_name = ?, age = ?, nationality = ?, experience = ?, role = ?, total_runs = ?, total_wickets = ? WHERE cricketer_id = ?";
 try (PreparedStatement ps = conn.prepareStatement(q)) {
            ps.setInt(1, cricketer.getTeamId());
            ps.setString(2, cricketer.getCricketerName());
            ps.setInt(3, cricketer.getAge());
            ps.setString(4, cricketer.getNationality());
            ps.setInt(5, cricketer.getExperience());
            ps.setString(6, cricketer.getRole());
            ps.setInt(7, cricketer.getTotalRuns());
            ps.setInt(8, cricketer.getTotalWickets());
            ps.setInt(9, cricketer.getCricketerId());
            ps.executeUpdate();

 }
    }

    @Override
    public void deleteCricketer(int cricketerId)throws SQLException {
  final String sql = "DELETE FROM cricketer WHERE cricketer_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cricketerId);
            ps.executeUpdate();
        }   
    }

    @Override
    public List<Cricketer> getAllCricketers() throws SQLException {
      
String sql = "SELECT * FROM cricketer";

        List<Cricketer> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql))
        {
         ResultSet rs=ps.executeQuery();
         while(rs.next())
         {
            Cricketer c=new Cricketer(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getInt(6),rs.getString(7),rs.getInt(8),rs.getInt(9));
            list.add(c);
         }
         return list;
        }
    
    }

}
