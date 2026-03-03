package com.edutech.progressive.service;

import com.edutech.progressive.entity.TicketBooking;

import java.sql.SQLException;
import java.util.List;

public interface TicketBookingService {
    List<TicketBooking> getAllTicketBookings()throws SQLException;

    int createBooking(TicketBooking ticketBooking)throws SQLException;

    void cancelBooking(int bookingId)throws SQLException;

    List<TicketBooking> getBookingsByUserEmail(String email)throws SQLException;

}
