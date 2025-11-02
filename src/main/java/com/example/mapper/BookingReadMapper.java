package com.example.mapper;

import com.example.database.entity.Booking;
import com.example.dto.BookingReadDto;
import com.example.dto.FlightReadDto;
import com.example.dto.SeatReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookingReadMapper implements Mapper<Booking, BookingReadDto> {
    private final SeatReadMapper seatReadMapper;
    private final FlightReadMapper flightReadMapper;

    @Override
    public BookingReadDto map(Booking object) {
        List<SeatReadDto> seatReadDtoList = Optional.ofNullable(object.getSeatList())
                .stream()
                .flatMap(List::stream)
                .map(seatReadMapper::map)
                .toList();

        FlightReadDto flightReadDto = Optional.ofNullable(object.getFlight())
                .map(flightReadMapper::map)
                .orElse(null);

        return BookingReadDto.builder()
                .id(object.getId())
                .bookingNumber(object.getBookingNumber())
                .bookingDate(object.getBookingDate())
                .flight(flightReadDto)
                .seatList(seatReadDtoList)
                .totalAmount(object.getTotalAmount())
                .status(object.getStatus())
                .build();
    }
}
