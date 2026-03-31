package com.example.dto;

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
    String code;
    String name;
    String city;
    String country;
    ZoneId timezone;
}
