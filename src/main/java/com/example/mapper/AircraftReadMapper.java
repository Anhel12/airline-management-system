package com.example.mapper;

import com.example.database.entity.Aircraft;
import com.example.dto.AircraftReadDto;
import org.springframework.stereotype.Component;

@Component
public class AircraftReadMapper implements Mapper<Aircraft, AircraftReadDto> {
    @Override
    public AircraftReadDto map(Aircraft object) {
        return AircraftReadDto.builder()
                .id(object.getId())
                .type(object.getType())
                .registrationNumber(object.getRegistrationNumber())
                .totalSeats(object.getTotalSeats())
                .build();
    }
}
