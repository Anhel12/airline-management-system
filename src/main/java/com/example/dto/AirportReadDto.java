package com.example.dto;

import lombok.*;

import java.time.ZoneId;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AirportReadDto{
    Long id;
    String code;
    String name;
    String city;
    String country;
    ZoneId timezone;
}
