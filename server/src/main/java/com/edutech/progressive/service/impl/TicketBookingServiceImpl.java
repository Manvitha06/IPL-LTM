package com.edutech.progressive.service.impl;
 
import com.edutech.progressive.entity.TicketBooking;
import com.edutech.progressive.repository.TicketBookingRepository;
import com.edutech.progressive.service.TicketBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import java.util.List;
 
@Service
public class TicketBookingServiceImpl implements TicketBookingService {
 
    @Autowired
    TicketBookingRepository ticketBookingRepository;
 
    @Override
    public List<TicketBooking> getAllTicketBookings() {
        return ticketBookingRepository.findAll();
    }
 
    @Override
    public int createBooking(TicketBooking ticketBooking) {
        return ticketBookingRepository.save(ticketBooking).getBookingId();
    }
 
    @Override
    public void cancelBooking(int bookingId) {
        ticketBookingRepository.deleteById(bookingId);
    }
 
    @Override
    public List<TicketBooking> getBookingsByUserEmail(String email) {
        return ticketBookingRepository.findByEmail(email);
    }
}