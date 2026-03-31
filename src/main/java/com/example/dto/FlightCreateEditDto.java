package com.example.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class FlightCreateEditDto{

        Long id;

        @NotBlank
        String flightNumber;

        @NotBlank
        Long departureAirportId;

        @NotBlank
        Long arrivalAirportId;

        @NotBlank
        @Future
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime departureDateTime;

        @NotBlank
        @Future
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime arrivalDateTime;
}
