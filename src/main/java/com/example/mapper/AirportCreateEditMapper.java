package com.example.mapper;

import com.example.database.Repository.BookingSeatRepository;
import com.example.database.Repository.FlightRepository;
import com.example.database.entity.*;
import com.example.dto.AirportCreateEditDto;
import com.example.dto.AirportReadDto;
import com.example.dto.BookingCreateEditDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AirportCreateEditMapper implements Mapper<AirportCreateEditDto, Airport> {

    @Override
    public Airport map(AirportCreateEditDto object) {
        Airport airport = new Airport();
        copy(object, airport);

        return airport;
    }

    @Override
    public Airport map(AirportCreateEditDto fromObject, Airport toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    public Airport copy(AirportCreateEditDto object, Airport airport){
        airport.setId(object.getId());
        airport.setCode(object.getCode());
        airport.setName(object.getName());
        airport.setCity(object.getCity());
        airport.setCountry(object.getCountry());
        airport.setTimezone(object.getTimezone());

        return airport;
    }

    public AirportCreateEditDto map(AirportReadDto fromObject) {
        AirportCreateEditDto airport = new AirportCreateEditDto();
        airport.setId(fromObject.getId());
        airport.setCode(fromObject.getCode());
        airport.setName(fromObject.getName());
        airport.setCity(fromObject.getCity());
        airport.setCountry(fromObject.getCountry());
        airport.setTimezone(fromObject.getTimezone());

        return airport;
    }
}

