package com.example.mapper;

import com.example.database.entity.Booking;
import com.example.dto.BookingPassengerDto;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class BookingPassengerReadMapper implements Mapper<Booking, BookingPassengerDto>{
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm");
    @Override
    public BookingPassengerDto map(Booking object) {
        return BookingPassengerDto.builder()
                .number(object.getBookingNumber())
                .bookingDate(object.getBookingDate())
                .totalAmount(object.getTotalAmount())
                .status(object.getStatus())
                .numberOfPassengers(object.getNumberOfPassenger())
                .departureDateTime(object.getFlight().getDepartureDateTime().format(formatter))
                .arrivalDateTime(object.getFlight().getArrivalDateTime().format(formatter))
                .departureCity(object.getFlight().getDepartureAirport().getCity())
                .arrivalCity(object.getFlight().getArrivalAirport().getCity())
                .build();
    }
}
