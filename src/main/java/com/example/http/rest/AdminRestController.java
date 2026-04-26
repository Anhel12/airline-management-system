package com.example.http.rest;

import com.example.dto.*;
import com.example.exception.FieldNotValidException;
import com.example.mapper.AirportCreateEditMapper;
import com.example.service.AirportService;
import com.example.service.BookingService;
import com.example.service.FlightService;
import com.example.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminRestController {
    private final PassengerService passengerService;
    private final BookingService bookingService;
    private final AirportService airportService;
    private final FlightService flightService;
    private final AirportCreateEditMapper airportCreateEditMapper;
    private final PasswordEncoder passwordEncoder;

    @PatchMapping("/airport")
    public ResponseEntity<?> airportUpdate(@RequestBody List<AirportReadDto> airportList){

        airportList.forEach(airportReadDto -> {
            AirportCreateEditDto dto = airportCreateEditMapper.map(airportReadDto);
            airportService.update(dto.getId(), dto);
        });

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/booking")
    public ResponseEntity<?> bookingUpdate(@RequestBody List<BookingCreateEditDto> bookingList){
        bookingList.forEach(bookingDto -> bookingService.update(bookingDto.getId(), bookingDto));

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/flight")
    public ResponseEntity<?> flightUpdate(@RequestBody List<FlightCreateEditDto> flightList){
        flightList.forEach(flightDto -> flightService.update(flightDto.getId(), flightDto));

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/passenger")
    public ResponseEntity<?> passengerUpdate(@RequestBody List<PassengerCreateEditDto> passengerList){
        passengerList.forEach(passengerDto -> passengerService.update(passengerDto.getId(), passengerDto));

        return ResponseEntity.ok().build();
    }

    @PutMapping("/airport")
    public ResponseEntity<?> airportCreate(@RequestBody @Validated AirportCreateEditDto dto,
                                           BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            throw new FieldNotValidException(bindingResult);
        }

        airportService.create(dto);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/booking")
    public ResponseEntity<?> bookingCreate(@RequestBody @Validated BookingCreateEditDto dto,
                                           BindingResult bindingResult){
        System.out.println("ПУТ МЕТОД БУКИНГА ==================================================");
        if(bindingResult.hasErrors()){
            throw new FieldNotValidException(bindingResult);
        }

        bookingService.create(dto);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/flight")
    public ResponseEntity<?> flightCreate(@RequestBody @Validated FlightCreateEditDto dto,
                                          BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            throw new FieldNotValidException(bindingResult);
        }

        flightService.create(dto);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/passenger")
    public ResponseEntity<?> passengerCreate(@RequestBody @Validated PassengerCreateEditDto dto,
                                             BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            throw new FieldNotValidException(bindingResult);
        }
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        passengerService.create(dto);

        return ResponseEntity.ok().build();
    }
}
