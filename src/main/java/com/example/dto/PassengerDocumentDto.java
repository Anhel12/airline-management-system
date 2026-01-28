package com.example.dto;

import com.example.database.entity.Sex;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record PassengerDocumentDto (
        @Size(min = 10, max = 10)
        String passportNumber,

        LocalDate birthDate,

        String firstName,
        String middleName,
        String lastName,

        Sex sex
) {
}
