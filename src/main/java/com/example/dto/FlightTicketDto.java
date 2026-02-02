package com.example.dto;


import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record FlightTicketDto(
        LocalDateTime departureDateTime,
        LocalDateTime arrivalDateTime,
        String departureAirportCode,
        String arrivalAirportCode,
        String flightNumber,
        String timeInAir,
        Long price
) {
}
