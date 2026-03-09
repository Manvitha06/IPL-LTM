package com.edutech.progressive.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.edutech.progressive.entity.Team;
@Repository
public interface TeamRepository extends JpaRepository <Team,Integer> {


 
  
    @Query("select t from Team t where t.teamId = :teamId")
    Team findByTeamId(@Param("teamId") int teamId);

    @Query("select t from Team t where t.teamName = :name")
    Team findByTeamName(@Param("name") String name);


}
