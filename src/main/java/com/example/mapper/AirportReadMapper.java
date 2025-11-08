package com.example.mapper;

import com.example.database.entity.Airport;
import com.example.dto.AirportReadDto;
import org.springframework.stereotype.Component;

@Component
public class AirportReadMapper implements Mapper<Airport, AirportReadDto> {
    @Override
    public AirportReadDto map(Airport object) {
        return AirportReadDto.builder()
                .id(object.getId())
                .code(object.getCode())
                .name(object.getName())
                .city(object.getCity())
                .country(object.getCountry())
                .timezone(object.getTimezone())
                .build();
    }
}
