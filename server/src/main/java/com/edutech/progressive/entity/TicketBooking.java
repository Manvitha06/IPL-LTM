package com.edutech.progressive.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table
public class TicketBooking {
    @Id
    @GeneratedValue
    private int bookingId;
    private String email;
    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;
    private int numberOfTickets;
    public TicketBooking() {
    }
    public TicketBooking(int bookingId, String email, Match match, int numberOfTickets) {
        this.bookingId = bookingId;
        this.email = email;
        this.match = match;
        this.numberOfTickets = numberOfTickets;
    }
    public int getBookingId() {
        return bookingId;
    }
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Match getMatch() {
        return match;
    }
    public void setMatch(Match match) {
        this.match = match;
    }
    public int getNumberOfTickets() {
        return numberOfTickets;
    }
    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }
}