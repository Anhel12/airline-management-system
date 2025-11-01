package com.example.database.dto;

import java.time.LocalDateTime;


public record FlightReadDto (
        Long id,
        String flightNumber,
        LocalDateTime departureDateTime,
        LocalDateTime arrivalDateTime,
        AircraftDto aircraftDto

){}
