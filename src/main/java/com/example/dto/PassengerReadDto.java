package com.example.dto;

import com.example.database.entity.Role;
import com.example.database.entity.Sex;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record PassengerReadDto(
        Long id,
        String phoneNumber,
        String email,
        String passportNumber,
        LocalDate birthDate,
        String FirstName,
        String MiddleName,
        String LastName,
        Sex sex,
        Role role
) {
}
