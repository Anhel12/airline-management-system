package com.example.dto;

import lombok.Builder;

import java.time.ZoneId;

@Builder
public record AirportReadDto(
        Long id,
        String code,
        String name,
        String city,
        String country,
        ZoneId timezone
) {
}
