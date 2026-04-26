package com.example.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

        @NotNull
        Long departureAirportId;

        @NotNull
        Long arrivalAirportId;

        String departureAirportName;

        String arrivalAirportName;

        @NotNull
        @Future
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        LocalDateTime departureDateTime;

        @NotNull
        @Future
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        LocalDateTime arrivalDateTime;
}
