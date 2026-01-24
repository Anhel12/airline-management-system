package com.example.dto;


import com.example.database.entity.Role;
import com.example.database.entity.Sex;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class PassengerCreateEditDto {

    String phoneNumber;

    @NotBlank(message = "Почта обязательна!")
    @Email(message = "Некорректный формат почты!")
    String email;

    String passportNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate birthDate;

    String firstName;
    String middleName;
    String lastName;

    Sex sex;

    Role role;

    @Size(min = 6, message = "Минимальная длина пароля 6 символов")
    @NotBlank(message = "Пароль обязателен!")
    String password;
}
