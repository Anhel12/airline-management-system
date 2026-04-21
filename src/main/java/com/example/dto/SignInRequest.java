package com.example.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignInRequest {
    @Email(message = "Email адрес должен быть в формате user@mail.com")
    @NotBlank(message = "Email адрес не может быть пустым")
    @Size(max = 255, message = "Email адрес не должен быть длинее 255 символов")
    private String email;

    @NotBlank(message = "Пароль не может быть пустым")
    @Size(max = 255, message = "Длина пароля не должна быть более 255 символов")
    private String password;
}
