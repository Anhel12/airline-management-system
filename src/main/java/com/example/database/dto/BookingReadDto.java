package com.example.database.dto;

import com.example.database.entity.Status;

import java.time.LocalDate;
import java.util.List;


public record BookingReadDto(
        Long id,
        String bookingNumber,
        LocalDate bookingDate,
        FlightReadDto flightReadDto,
        List<String> seatDtoList,
        Integer totalAmount,
        Status status
) {
}
