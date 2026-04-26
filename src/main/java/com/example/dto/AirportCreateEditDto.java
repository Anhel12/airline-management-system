package com.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZoneId;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AirportCreateEditDto{
    Long id;
    @NotBlank(message = "Код аэропорта не может быть пустым")
    String code;
    @NotBlank(message = "Название аэропорта не может быть пустым")
    String name;
    @NotBlank(message = "Город аэропорта не может быть пустым")
    String city;
    @NotBlank(message = "Страна аэропорта не может быть пустой")
    String country;
    @NotNull(message = "Временная зона не может быть пустой")
    ZoneId timezone;
}
