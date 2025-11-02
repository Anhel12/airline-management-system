package com.example.mapper;

import com.example.database.entity.Flight;
import com.example.dto.AircraftReadDto;
import com.example.dto.FlightReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FlightReadMapper implements Mapper<Flight, FlightReadDto> {
    private final AircraftReadMapper aircraftReadMapper;

    @Override
    public FlightReadDto map(Flight object) {
        AircraftReadDto aircraftReadDto = Optional.ofNullable(object.getAircraft())
                .map(aircraftReadMapper::map)
                .orElse(null);

        return FlightReadDto.builder()
                .id(object.getId())
                .flightNumber(object.getFlightNumber())
                .departureDateTime(object.getDepartureDateTime())
                .arrivalDateTime(object.getArrivalDateTime())
                .aircraft(aircraftReadDto)
                .build();
    }
}
