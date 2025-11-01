package com.example.database.dto;

import com.example.database.entity.Role;
import com.example.database.entity.Sex;

import java.time.LocalDate;

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
