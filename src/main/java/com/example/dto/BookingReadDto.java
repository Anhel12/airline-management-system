package com.example.dto;

import com.example.database.entity.Status;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record BookingReadDto(
        Long id,
        String bookingNumber,
        LocalDate bookingDate,
        FlightReadDto flight,
        List<SeatReadDto> seatList,
        Integer totalAmount,
        Status status
) {
}
