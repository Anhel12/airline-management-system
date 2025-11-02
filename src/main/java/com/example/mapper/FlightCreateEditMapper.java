package com.example.mapper;

import com.example.database.Repository.AircraftRepository;
import com.example.database.entity.Aircraft;
import com.example.database.entity.Flight;
import com.example.dto.FlightCreateEditDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FlightCreateEditMapper implements Mapper<FlightCreateEditDto, Flight> {
    private final AircraftRepository aircraftRepository;

    @Override
    public Flight map(FlightCreateEditDto object) {
        Flight flight = new Flight();
        copy(object, flight);
        return flight;
    }

    @Override
    public Flight map(FlightCreateEditDto fromObject, Flight toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    public void copy(FlightCreateEditDto flightDto, Flight flight){
        flight.setFlightNumber(flightDto.flightNumber());
        flight.setDepartureDateTime(flightDto.departureDateTime());
        flight.setArrivalDateTime(flightDto.arrivalDateTime());
        flight.setAircraft(getAircraft(flightDto.aircraft_id()));
    }

    public Aircraft getAircraft(Long aircraftId){
        return Optional.ofNullable(aircraftId)
                .flatMap(aircraftRepository::findById)
                .orElse(null);
    }
}
