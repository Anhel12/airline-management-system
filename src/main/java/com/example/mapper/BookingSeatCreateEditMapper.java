package com.example.mapper;

import com.example.database.Repository.BookingRepository;
import com.example.database.Repository.SeatRepository;
import com.example.database.entity.Booking;
import com.example.database.entity.BookingSeat;
import com.example.database.entity.Seat;
import com.example.dto.BookingCreateEditDto;
import com.example.dto.BookingSeatCreateEditDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookingSeatCreateEditMapper implements Mapper<BookingSeatCreateEditDto, BookingSeat> {
    private final BookingRepository bookingRepository;
    private final SeatRepository seatRepository;

    @Override
    public BookingSeat map(BookingSeatCreateEditDto object) {
        BookingSeat bookingSeat = new BookingSeat();
        copy(object, bookingSeat);
        return bookingSeat;
    }

    @Override
    public BookingSeat map(BookingSeatCreateEditDto fromObject, BookingSeat toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    public void copy(BookingSeatCreateEditDto bookingSeatDto, BookingSeat bookingSeat){
        bookingSeat.setBooking(getBooking(bookingSeatDto.booking_id()));
        bookingSeat.setSeat(getSeat(bookingSeatDto.seat_id()));
    }

    public Booking getBooking(Long booking_id){
        return Optional.ofNullable(booking_id)
                .flatMap(bookingRepository::findById)
                .orElse(null);
    }

    public Seat getSeat(Long seat_id){
        return Optional.ofNullable(seat_id)
                .flatMap(seatRepository::findById)
                .orElse(null);
    }
}
