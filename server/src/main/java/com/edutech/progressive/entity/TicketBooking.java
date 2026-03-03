package com.edutech.progressive.entity;
import javax.persistence.*;
@Entity
@Table(name = "ticket_booking")
public class TicketBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private int bookingId;
    @ManyToOne(optional = false)
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;
    @Column(nullable = false, length = 100)
    private String email;
    @Column(name = "number_of_tickets", nullable = false)
    private int numberOfTickets;
    public TicketBooking() { }
    public TicketBooking(int bookingId, String email, Match match, int numberOfTickets) {
        this.bookingId = bookingId;
        this.email = email;
        this.match = match;
        this.numberOfTickets = numberOfTickets;
    }
    public int getBookingId() { return bookingId; }
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }
    public Match getMatch() { return match; }
    public void setMatch(Match match) { this.match = match; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public int getNumberOfTickets() { return numberOfTickets; }
    public void setNumberOfTickets(int numberOfTickets) { this.numberOfTickets = numberOfTickets; }
}