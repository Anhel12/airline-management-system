package com.example.dto;

import jakarta.validation.constraints.NotBlank;

public record BookingSeatCreateEditDto(
        @NotBlank
        Long booking_id,
        @NotBlank
        Long seat_id
) {
}
