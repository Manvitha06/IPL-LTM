package com.edutech.progressive.service;

import com.edutech.progressive.entity.TicketBooking;

import java.util.List;

public interface TicketBookingService {
    List<TicketBooking> getAllTicketBookings();

    int createBooking(TicketBooking ticketBooking);

    void cancelBooking(int bookingId);

    List<TicketBooking> getBookingsByUserEmail(String email);

}
