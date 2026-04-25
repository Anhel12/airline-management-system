package com.example.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class PassengerSettingsDto {
//    @Max(value = 11, message = "Максимальная длина номера телефона 11 символов")
//    @Min(value = 11, message = "Минимальная длина номера телефона 11 символов")
    String phoneNumber;

    @NotBlank(message = "Почта обязательна!")
    @Email(message = "Некорректный формат почты!")
    String email;
}
