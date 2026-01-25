package com.example.mapper;

import com.example.database.entity.Booking;
import com.example.dto.BookingPassengerDto;
import org.springframework.stereotype.Component;

@Component
public class BookingPassengerReadMapper implements Mapper<Booking, BookingPassengerDto>{
    @Override
    public BookingPassengerDto map(Booking object) {
        return BookingPassengerDto.builder()
                .number(object.getBookingNumber())
                .bookingDate(object.getBookingDate())
                .totalAmount(object.getTotalAmount())
                .status(object.getStatus())
                .numberOfPassengers(object.getNumberOfPassenger())
                .departureDateTime(object.getFlight().getDepartureDateTime())
                .arrivalDateTime(object.getFlight().getArrivalDateTime())
                .departureCity(object.getFlight().getDepartureAirport().getCity())
                .arrivalCity(object.getFlight().getArrivalAirport().getCity())
                .build();
    }
}
