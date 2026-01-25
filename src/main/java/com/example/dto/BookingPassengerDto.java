package com.example.dto;


import com.example.database.entity.Status;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record BookingPassengerDto (
        String number,
        LocalDate bookingDate,
        Integer totalAmount,
        Status status,
        Integer numberOfPassengers,
        LocalDateTime departureDateTime,
        LocalDateTime arrivalDateTime,
        String departureCity,
        String arrivalCity
){}
