
package com.edutech.progressive.entity;

import javax.persistence.*; // Boot 2.x; use jakarta.persistence.* for Boot 3.x
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import java.util.Date;

@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private int matchId;

    // Make FKs nullable to avoid default 0
    @Column(name = "first_team_id")
    private Integer firstTeamId;

    @Column(name = "second_team_id")
    private Integer secondTeamId;

    @Temporal(TemporalType.DATE)
    @Column(name = "match_date", nullable = false)
    private Date matchDate;

    private String venue;
    private String result;
    private String status;

    @Column(name = "winner_team_id")
    private Integer winnerTeamId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "first_team_id", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private Team firstTeam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "second_team_id", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private Team secondTeam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "winner_team_id", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private Team winnerTeam;

    public Match() {}

    public Match(int matchId, Integer firstTeamId, Integer secondTeamId, Date matchDate,
                 String venue, String result, String status, Integer winnerTeamId) {
        this.matchId = matchId;
        this.firstTeamId = firstTeamId;
        this.secondTeamId = secondTeamId;
        this.matchDate = matchDate;
        this.venue = venue;
        this.result = result;
        this.status = status;
        this.winnerTeamId = winnerTeamId;
    }

    public int getMatchId() { return matchId; }
    public void setMatchId(int matchId) { this.matchId = matchId; }

    public Integer getFirstTeamId() { return firstTeamId; }
    public void setFirstTeamId(Integer firstTeamId) { this.firstTeamId = firstTeamId; }

    public Integer getSecondTeamId() { return secondTeamId; }
    public void setSecondTeamId(Integer secondTeamId) { this.secondTeamId = secondTeamId; }

    public Date getMatchDate() { return matchDate; }
    public void setMatchDate(Date matchDate) { this.matchDate = matchDate; }

    public String getVenue() { return venue; }
    public void setVenue(String venue) { this.venue = venue; }

    public String getResult() { return result; }
    public void setResult(String result) { this.result = result; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Integer getWinnerTeamId() { return winnerTeamId; }
    public void setWinnerTeamId(Integer winnerTeamId) { this.winnerTeamId = winnerTeamId; }

    public Team getFirstTeam() { return firstTeam; }
    public void setFirstTeam(Team firstTeam) { this.firstTeam = firstTeam; }

    public Team getSecondTeam() { return secondTeam; }
    public void setSecondTeam(Team secondTeam) { this.secondTeam = secondTeam; }

    public Team getWinnerTeam() { return winnerTeam; }
    public void setWinnerTeam(Team winnerTeam) { this.winnerTeam = winnerTeam; }
}
