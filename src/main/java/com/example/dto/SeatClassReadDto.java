package com.example.dto;

import com.example.database.entity.FareClass;
import lombok.Builder;

@Builder
public record SeatClassReadDto(
        Long id,
        FareClass fareClass
) {
}
