package com.example.mapper;

import com.example.database.entity.Seat;
import com.example.dto.AircraftReadDto;
import com.example.dto.SeatClassReadDto;
import com.example.dto.SeatReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SeatReadMapper implements Mapper<Seat, SeatReadDto> {
    private final SeatClassReadMapper seatClassReadMapper;
    private final AircraftReadMapper aircraftReadMapper;

    @Override
    public SeatReadDto map(Seat object) {
        SeatClassReadDto seatClassReadDto = Optional.ofNullable(object.getSeatClass())
                .map(seatClassReadMapper::map)
                .orElse(null);

        AircraftReadDto aircraftReadDto = Optional.ofNullable(object.getAircraft())
                .map(aircraftReadMapper::map)
                .orElse(null);

        return SeatReadDto.builder()
                .id(object.getId())
                .aircraft(aircraftReadDto)
                .seatNumber(object.getSeatNumber())
                .seatClass(seatClassReadDto)
                .build();
    }
}
