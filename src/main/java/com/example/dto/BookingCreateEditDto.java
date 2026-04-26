package com.example.dto;


import com.example.database.entity.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BookingCreateEditDto{

        Long id;

        @NotBlank
        String bookingNumber;

        @NotNull
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate bookingDate;
        @NotNull
        Long flight_id;

        List<Long> seat_id;

        @NotNull
        @Positive
        Integer totalAmount;

        Status status;
}
