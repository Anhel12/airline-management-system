package com.example.mapper;

import com.example.database.entity.Flight;
import com.example.dto.AircraftReadDto;
import com.example.dto.AirportReadDto;
import com.example.dto.FlightReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FlightReadMapper implements Mapper<Flight, FlightReadDto> {
    private final AirportReadMapper airportReadMapper;

    @Override
    public FlightReadDto map(Flight object) {
        AirportReadDto departureAirportDto = airportReadMapper.map(object.getDepartureAirport());
        AirportReadDto arrivalAirportDto = airportReadMapper.map(object.getArrivalAirport());

        return FlightReadDto.builder()
                .id(object.getId())
                .flightNumber(object.getFlightNumber())
                .departureAirport(departureAirportDto)
                .arrivalAirport(arrivalAirportDto)
                .departureDateTime(object.getDepartureDateTime())
                .arrivalDateTime(object.getArrivalDateTime())
                .build();
    }
}
