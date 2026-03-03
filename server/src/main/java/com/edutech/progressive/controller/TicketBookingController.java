package com.edutech.progressive.controller;

import com.edutech.progressive.entity.TicketBooking;
import com.edutech.progressive.service.TicketBookingService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/ticket")
@CrossOrigin
public class TicketBookingController {

    private final TicketBookingService ticketBookingService;

    public TicketBookingController(TicketBookingService ticketBookingService) {
        this.ticketBookingService = ticketBookingService;
    }

    // GET /ticket
    @GetMapping
    public ResponseEntity<List<TicketBooking>> getAllBookings() {
        try {
            return ResponseEntity.ok(ticketBookingService.getAllTicketBookings());
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // POST /ticket
    @PostMapping
    public ResponseEntity<Integer> createBooking(@RequestBody TicketBooking ticketBooking) {
        try {
            int id = ticketBookingService.createBooking(ticketBooking);
            return ResponseEntity.status(HttpStatus.CREATED).body(id);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // DELETE /ticket/{bookingID}
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> cancelBooking(@PathVariable int bookingId) {
        try {
            ticketBookingService.cancelBooking(bookingId);
            return ResponseEntity.noContent().build();
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // GET /ticket/user/{email}
    @GetMapping("/user/{email}")
    public ResponseEntity<List<TicketBooking>> getBookingsByUserEmail(@PathVariable String email) {
        try {
            return ResponseEntity.ok(ticketBookingService.getBookingsByUserEmail(email));
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
