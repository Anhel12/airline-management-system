package com.example.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    @Email(message = "Email адрес должен быть в формате user@mail.com")
    @NotBlank(message = "Email адрес не может быть пустым")
    @Size(max = 255, message = "Email адрес не должен быть длинее 255 символов")
    private String email;

    @NotBlank(message = "Пароль не может быть пустым")
    @Size(min = 6, max = 255, message = "Длина пароля не должна быть от 6 до 255 символов")
    private String password;
}
