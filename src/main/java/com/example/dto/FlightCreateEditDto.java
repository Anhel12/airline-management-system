package com.example.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


public record FlightCreateEditDto(
    @NotBlank
    String flightNumber,

    @NotBlank
    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime departureDateTime,

    @NotBlank
    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime arrivalDateTime,

    @NotBlank
    Long aircraft_id

) {


}
