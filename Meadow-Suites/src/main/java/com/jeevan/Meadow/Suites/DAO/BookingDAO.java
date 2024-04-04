package com.jeevan.Meadow.Suites.DAO;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeevan.Meadow.Suites.model.BookedRoom;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingDAO extends JpaRepository<BookedRoom, Long> {

    List<BookedRoom> findByRoomId(Long roomId);

    Optional<BookedRoom> findByBookingConfirmationCode(String confirmationCode);

    List<BookedRoom> findByGuestEmail(String email);
}
