package com.example.mapper;

import com.example.Utils.TimeUtils;
import com.example.database.entity.Flight;
import com.example.dto.FlightTicketDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FlightTicketMapper implements Mapper<Flight, FlightTicketDto>{

    @Override
    public FlightTicketDto map(Flight object) {
        return FlightTicketDto.builder()
                .departureDateTime(object.getDepartureDateTime())
                .arrivalDateTime(object.getArrivalDateTime())
                .departureAirportCode(object.getDepartureAirport().getCode())
                .arrivalAirportCode(object.getArrivalAirport().getCode())
                .flightNumber(object.getFlightNumber())
                .timeInAir(TimeUtils.durationFormat(
                        TimeUtils.getDurationBetween(object.getDepartureDateTime(),
                            object.getArrivalDateTime())))
                .price(TimeUtils.getDurationBetween(object.getDepartureDateTime(),
                        object.getArrivalDateTime()).toMinutes() * 100)
                .build();
    }
}
