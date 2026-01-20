package com.example.dto;


import com.example.database.entity.Role;
import com.example.database.entity.Sex;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Builder
public record PassengerCreateEditDto (
        @UniqueElements
        String phoneNumber,

        @UniqueElements
        String email,

        String passportNumber,

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate birthDate,

        String firstName,
        String middleName,
        String lastName,

        Sex sex,

        Role role,

        @NotBlank
        String password
){
}
