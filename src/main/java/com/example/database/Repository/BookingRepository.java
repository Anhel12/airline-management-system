package com.example.database.Repository;

import com.example.database.entity.Booking;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>, QuerydslPredicateExecutor<Booking> {
    @Query("select COUNT(*) from Booking")
    public Integer countAll();

    @Query("select sum(b.totalAmount) from Booking b")
    public Integer sumByAllBookingAmount();

    @Query("select sum(b.totalAmount) from Booking b " +
            "where b.bookingDate between :firstDate and :secondDate")
    public Optional<Integer> sumByAllBookingAmountBetweenTwoDates(LocalDate firstDate, LocalDate secondDate);
}
