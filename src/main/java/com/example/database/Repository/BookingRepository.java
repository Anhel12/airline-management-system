package com.example.database.Repository;

import com.example.database.entity.Booking;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT DISTINCT b FROM Booking b " +
            "LEFT JOIN FETCH b.flight f " +
            "LEFT JOIN FETCH f.departureAirport " +
            "LEFT JOIN FETCH f.arrivalAirport " +
            "LEFT JOIN FETCH b.bookingSeatList bs " +
            "LEFT JOIN FETCH bs.seat s " +
            "LEFT JOIN FETCH s.seatClass " +
            "LEFT JOIN FETCH s.aircraft")
    List<Booking> findAll();

    @Query("SELECT DISTINCT b FROM Booking b " +
            "LEFT JOIN FETCH b.flight f " +
            "LEFT JOIN FETCH f.departureAirport " +
            "LEFT JOIN FETCH f.arrivalAirport " +
            "LEFT JOIN FETCH b.bookingSeatList bs " +
            "LEFT JOIN FETCH bs.seat s " +
            "LEFT JOIN FETCH s.seatClass " +
            "LEFT JOIN FETCH s.aircraft " +
            "WHERE b.id = :id")
    Optional<Booking> findById(Long id);
}
