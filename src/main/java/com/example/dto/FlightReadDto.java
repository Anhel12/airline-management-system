package com.example.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record FlightReadDto (
        Long id,
        String flightNumber,
        LocalDateTime departureDateTime,
        LocalDateTime arrivalDateTime,
        AircraftDto aircraftDto

){}
