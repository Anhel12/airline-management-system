package com.example.mapper;

import com.example.database.Repository.FlightRepository;
import com.example.database.Repository.SeatRepository;
import com.example.database.entity.Booking;
import com.example.database.entity.Flight;
import com.example.database.entity.Seat;
import com.example.dto.BookingCreateEditDto;
import com.example.dto.BookingReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookingCreateEditMapper implements Mapper<BookingCreateEditDto, Booking> {
    private final FlightRepository flightRepository;
    private final SeatRepository seatRepository;

    @Override
    public Booking map(BookingCreateEditDto object) {
        Booking booking = new Booking();
        copy(object, booking);
        return booking;
    }

    @Override
    public Booking map(BookingCreateEditDto fromObject, Booking toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    public void copy(BookingCreateEditDto bookingDto, Booking booking){
        booking.setBookingNumber(bookingDto.bookingNumber());
        booking.setBookingDate(bookingDto.bookingDate());
        booking.setFlight(getFlight(bookingDto.flight_id()));
        booking.setSeatList(getSeat(bookingDto.seat_id()));
        booking.setTotalAmount(bookingDto.totalAmount());
        booking.setStatus(bookingDto.status());
    }

    public Flight getFlight(Long flightId){
        return Optional.ofNullable(flightId)
                .flatMap(flightRepository::findById)
                .orElse(null);
    }

    public List<Seat> getSeat(List<Long> seatIds){
        return Optional.ofNullable(seatIds)
                .map(seatRepository::findAllById)
                .orElse(null);

    }
}
