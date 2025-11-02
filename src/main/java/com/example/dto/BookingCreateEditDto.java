package com.example.dto;


import com.example.database.entity.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;


public record BookingCreateEditDto(

        @NotBlank
        String bookingNumber,

        @NotBlank
        @Past
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate bookingDate,

        @NotBlank
        Long flight_id,

        @NotBlank
        List<Long> seat_id,

        @NotBlank
        @Positive
        Integer totalAmount,

        Status status
) {}
