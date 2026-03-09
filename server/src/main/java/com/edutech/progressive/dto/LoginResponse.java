package com.edutech.progressive.dto;



public class LoginResponse {
    private String token;
    private String role;
    private Integer userId;
    private Integer teamId; // present if TEAM_MANAGER

    public LoginResponse() {}
    public LoginResponse(String token, String role, Integer userId, Integer teamId) {
        this.token = token; this.role = role; this.userId = userId; this.teamId = teamId;
    }
    

    public LoginResponse(String token, String role, Integer userId) {
        this.token = token;
        this.role = role;
        this.userId = userId;
    }
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    public Integer getTeamId() { return teamId; }
    public void setTeamId(Integer teamId) { this.teamId = teamId; }
}

