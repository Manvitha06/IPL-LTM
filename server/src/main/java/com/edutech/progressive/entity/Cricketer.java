package com.edutech.progressive.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;

@Entity
@Table(name = "cricketer")
public class Cricketer implements Comparable<Cricketer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cricketer_id")
    private int cricketerId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @Column(name = "cricketer_name")
    private String cricketerName;

    private int age;
    private String nationality;
    private int experience;
    private String role;

    @Column(name = "total_runs")
    private int totalRuns;

    @Column(name = "total_wickets")
    private int totalWickets;

    public Cricketer() {}

    // ----- getters/setters for other fields -----
    public int getCricketerId() { return cricketerId; }
    public void setCricketerId(int cricketerId) { this.cricketerId = cricketerId; }

    public Team getTeam() { return team; }
    public void setTeam(Team team) { this.team = team; }

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
    @JsonProperty("teamId")
    public int getTeamId() {
        return (team != null ? team.getTeamId() : 0);
    }

    @JsonProperty("teamId")
    public void setTeamId(int teamId) {
        if (this.team == null) {
            this.team = new Team();
        }
        this.team.setTeamId(teamId); // reference by id only
    }

    @Override
    public int compareTo(Cricketer o) {
        return Integer.compare(this.experience, o.getExperience());
    }
}