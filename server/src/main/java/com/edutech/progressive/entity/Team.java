package com.edutech.progressive.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Team implements Comparable<Team> {
    @Id
    @GeneratedValue
    private int teamId;
    private String teamName;
    private String location;
    private String ownerName;
    private int establishmentYear;
    // @OneToMany(mappedBy = "team")
    // private List<Cricketer> players;
    public Team() {
    }
    public Team(int teamId, String teamName, String location, String ownerName, int establishmentYear) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.location = location;
        this.ownerName = ownerName;
        this.establishmentYear = establishmentYear;
    }
    
    public int getTeamId() {
        return teamId;
    }
    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }
    public String getTeamName() {
        return teamName;
    }
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getOwnerName() {
        return ownerName;
    }
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
    public int getEstablishmentYear() {
        return establishmentYear;
    }
    public void setEstablishmentYear(int establishmentYear) {
        this.establishmentYear = establishmentYear;
    }
    @Override
    public int compareTo(Team o) {
       return this.teamName.compareTo(o.getTeamName());
    }
    // public List<Cricketer> getPlayers() {
    //     return players;
    // }
    // public void setPlayers(List<Cricketer> players) {
    //     this.players = players;
    // }
    // public void addCricketer(Cricketer c){
    //     players.add(c);
    // }
}