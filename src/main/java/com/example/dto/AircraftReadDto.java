package com.example.dto;

import lombok.Builder;

@Builder
public record AircraftReadDto(
        Long id,
        String type,
        String registrationNumber,
        Integer totalSeats
) {
}
