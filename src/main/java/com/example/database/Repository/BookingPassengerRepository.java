package com.example.database.Repository;

import com.example.database.entity.Booking;
import com.example.database.entity.BookingPassenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingPassengerRepository extends JpaRepository<BookingPassenger, Long> {

    @Query("SELECT DISTINCT b FROM BookingPassenger bp " +
            "LEFT JOIN FETCH bp.booking b " +
            "LEFT JOIN FETCH b.flight f " +
            "LEFT JOIN FETCH f.departureAirport " +
            "LEFT JOIN FETCH f.arrivalAirport " +
            "WHERE b.status = 'CONFIRMED' AND bp.passenger.email = :email")
    List<Booking> findFullActiveBookingByPassengerEmail(String email);

    @Query("SELECT DISTINCT b FROM BookingPassenger bp " +
            "LEFT JOIN FETCH bp.booking b " +
            "LEFT JOIN FETCH b.flight f " +
            "LEFT JOIN FETCH f.departureAirport " +
            "LEFT JOIN FETCH f.arrivalAirport " +
            "WHERE b.status = 'COMPLETED' OR b.status = 'CANCELED' AND bp.passenger.email = :email")
    List<Booking> findFullArchiveBookingByPassengerEmail(String email);
}
