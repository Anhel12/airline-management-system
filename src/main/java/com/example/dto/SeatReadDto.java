package com.example.dto;

import lombok.Builder;

@Builder
public record SeatReadDto(
        Long id,
        AircraftReadDto aircraft,
        String seatNumber,
        SeatClassReadDto seatClass,
        BookingReadDto booking
){
}
