package com.example.mapper;

import com.example.database.Repository.AircraftRepository;
import com.example.database.Repository.AirportRepository;
import com.example.database.entity.Aircraft;
import com.example.database.entity.Airport;
import com.example.database.entity.Flight;
import com.example.dto.FlightCreateEditDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FlightCreateEditMapper implements Mapper<FlightCreateEditDto, Flight> {
    private final AirportRepository airportRepository;

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
        flight.setFlightNumber(flightDto.getFlightNumber());
        flight.setDepartureAirport(getAirport(flightDto.getDepartureAirportId()));
        flight.setArrivalAirport(getAirport(flightDto.getArrivalAirportId()));
        flight.setDepartureDateTime(flightDto.getDepartureDateTime());
        flight.setArrivalDateTime(flightDto.getArrivalDateTime());

    }

    public Airport getAirport(Long airportId){
        return Optional.ofNullable(airportId)
                .flatMap(airportRepository::findById)
                .orElse(null);
    }

    public FlightCreateEditDto map(Flight fromObject){
        FlightCreateEditDto flightCreateEditDto = new FlightCreateEditDto(
                fromObject.getId(),
                fromObject.getFlightNumber(),
                fromObject.getDepartureAirport().getId(),
                fromObject.getArrivalAirport().getId(),
                fromObject.getDepartureAirport().getName(),
                fromObject.getArrivalAirport().getName(),
                fromObject.getDepartureDateTime(),
                fromObject.getArrivalDateTime()
        );

        return flightCreateEditDto;
    }
}
