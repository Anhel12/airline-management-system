package com.example.database.dto;


public record AircraftDto(
        Long id,
        String type,
        String registrationNumber,
        Integer totalSeats
) {
}
