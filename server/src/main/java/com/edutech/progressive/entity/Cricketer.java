package com.edutech.progressive.entity;

// If Spring Boot 2.x:
import javax.persistence.*;
// If Spring Boot 3.x, replace the above with: import jakarta.persistence.*;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.edutech.progressive.entity.Team;

@Entity
@Table(name = "cricketer")
public class Cricketer implements Comparable<Cricketer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cricketer_id")
    private int cricketerId;

    // Nullable FK to avoid default 0 → Team#0 fetch
    @Column(name = "team_id")
    private Integer teamId;

    @Column(name = "cricketer_name", nullable = false, length = 100)
    private String cricketerName;

    private int age;
    private String nationality;
    private int experience;

    @Column(length = 50) // Allowed: “Batsman”, “Bowler”, “All-rounder”, “Wicketkeeper”
    private String role;

    @Column(name = "total_runs")
    private int totalRuns;

    @Column(name = "total_wickets")
    private int totalWickets;

    /**
     * Association is READ-ONLY via team_id and:
     *  - LAZY to avoid eager proxy loading
     *  - @NotFound(IGNORE) to avoid exceptions if Team row not present (e.g., teamId=0/null)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private Team team;

    public Cricketer() {}

    public Cricketer(int cricketerId, Integer teamId, String cricketerName, int age,
                     String nationality, int experience, String role, int totalRuns, int totalWickets) {
        this.cricketerId = cricketerId;
        this.teamId = teamId;
        this.cricketerName = cricketerName;
        this.age = age;
        this.nationality = nationality;
        this.experience = experience;
        this.role = role;
        this.totalRuns = totalRuns;
        this.totalWickets = totalWickets;
    }

    public int getCricketerId() { return cricketerId; }
    public void setCricketerId(int cricketerId) { this.cricketerId = cricketerId; }

    public Integer getTeamId() { return teamId; }
    public void setTeamId(Integer teamId) { this.teamId = teamId; }

    public String getCricketerName() { return cricketerName; }
    public void setCricketerName(String cricketerName) { this.cricketerName = cricketerName; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }

    public int getExperience() { return experience; }
    public void setExperience(int experience) { this.experience = experience; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public int getTotalRuns() { return totalRuns; }
    public void setTotalRuns(int totalRuns) { this.totalRuns = totalRuns; }

    public int getTotalWickets() { return totalWickets; }
    public void setTotalWickets(int totalWickets) { this.totalWickets = totalWickets; }

    public Team getTeam() { return team; }
    public void setTeam(Team team) { this.team = team; }

    @Override
    public int compareTo(Cricketer o) {
        return Integer.compare(this.experience, o.experience);
    }
}