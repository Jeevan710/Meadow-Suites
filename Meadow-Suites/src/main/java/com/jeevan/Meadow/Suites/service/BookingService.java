package com.jeevan.Meadow.Suites.service;

import java.util.List;
import com.jeevan.Meadow.Suites.model.BookedRoom;

public interface BookingService {

    List<BookedRoom> getAllBookingsByRoomId(Long roomId);

    String saveBooking(Long roomId, BookedRoom bookingRequest);

    BookedRoom findByBookingConfirmationCode(String confirmationCode);

    List<BookedRoom> getAllBookings();

    List<BookedRoom> getBookingsByUserEmail(String email);

	void cancelBooking(Long bookingId);
}
