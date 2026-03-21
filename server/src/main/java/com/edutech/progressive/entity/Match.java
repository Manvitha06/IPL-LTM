package com.edutech.progressive.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="matches")
public class Match {
    @Id
    @GeneratedValue
    private int matchId;
    // private int firstTeamId;
    // private int secondTeamId;
    @Temporal(TemporalType.DATE)
    private Date matchDate;
    private String venue;
    private String result;
    private String status;
    private int winnerTeamId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "firstTeam_Id", referencedColumnName = "teamId")
    private Team firstTeam;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="secondTeam_Id",referencedColumnName = "teamId")
    private Team secondTeam;
    public Match() {
    }
    public Match(Date matchDate, String venue, String result, String status, int winnerTeamId, Team firstTeam,
            Team secondTeam) {
        this.matchDate = matchDate;
        this.venue = venue;
        this.result = result;
        this.status = status;
        this.winnerTeamId = winnerTeamId;
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
    }
    
    public Team getFirstTeam() {
        return firstTeam;
    }
    public void setFirstTeam(Team firstTeam) {
        this.firstTeam = firstTeam;
    }
    public Team getSecondTeam() {
        return secondTeam;
    }
    public void setSecondTeam(Team secondTeam) {
        this.secondTeam = secondTeam;
    }
    public int getMatchId() {
        return matchId;
    }
    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }
    public int getFirstTeamId() {
        return firstTeam.getTeamId();
    }
    public void setFirstTeamId(int firstTeamId) {
        // this.firstTeamId = firstTeamId;
        firstTeam.setTeamId(firstTeamId);
    }
    public int getSecondTeamId() {
        return secondTeam.getTeamId();
    }
    public void setSecondTeamId(int secondTeamId) {
        // this.secondTeamId = secondTeamId;
        secondTeam.setTeamId(secondTeamId);
    }
    public Date getMatchDate() {
        return matchDate;
    }
    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }
    public String getVenue() {
        return venue;
    }
    public void setVenue(String venue) {
        this.venue = venue;
    }
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getWinnerTeamId() {
        return winnerTeamId;
    }
    public void setWinnerTeamId(int winnerTeamId) {
        this.winnerTeamId = winnerTeamId;
    }
}