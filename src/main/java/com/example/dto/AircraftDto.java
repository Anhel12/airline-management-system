package com.example.dto;

import lombok.Builder;

@Builder
public record AircraftDto(
        Long id,
        String type,
        String registrationNumber,
        Integer totalSeats
) {
}
