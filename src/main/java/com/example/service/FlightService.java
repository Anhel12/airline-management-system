package com.example.service;

import com.example.database.Repository.FilterFlightRepositoryImpl;
import com.example.database.Repository.FlightRepository;
import com.example.dto.*;
import com.example.mapper.FlightCreateEditMapper;
import com.example.mapper.FlightReadMapper;
import com.example.mapper.FlightTicketMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.example.database.entity.QFlight.flight;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FlightService {
    private final FlightRepository flightRepository;
    private final FlightReadMapper flightReadMapper;
    private final FlightCreateEditMapper flightCreateEditMapper;
    private final FlightTicketMapper flightTicketMapper;

    public List<FlightReadDto> findAll(){
        return flightRepository.findAll().stream()
                .map(flightReadMapper::map)
                .toList();
    }

    public List<FlightTicketDto> findAllForTicket(){
        return flightRepository.findAll().stream()
                .map(flightTicketMapper::map)
                .toList();
    }

    public List<FlightTicketDto> findAllForTicket(FlightFilter filter, Boolean isStarting){
        if(isStarting){
            return flightRepository.findAllByFilterStartTicket(filter).stream()
                    .map(flightTicketMapper::map)
                    .toList();
        }

        return flightRepository.findAllByFilterReturnTicket(filter).stream()
                .map(flightTicketMapper::map)
                .toList();
    }

    public Page<FlightTicketDto> findAllForTicket(Pageable pageable){
        return flightRepository.findAll(pageable)
                .map(flightTicketMapper::map);
    }

    public Page<FlightTicketDto> findAllForTicket(FlightFilter filter, Pageable pageable, Boolean isStarting){
        if(isStarting){
            var predicate = QPredicates.builder()
                    .add(filter.getDepartureCity(), flight.departureAirport.city::containsIgnoreCase)
                    .add(filter.getArrivalCity(), flight.arrivalAirport.city::containsIgnoreCase)
                    .add(filter.getDepartureDate(), date ->
                            flight.departureDateTime.between(date.atStartOfDay(), date.plusDays(1).atStartOfDay()))
                    .build();

            return flightRepository.findAll(predicate, pageable)
                    .map(flightTicketMapper::map);
        }

        var predicate = QPredicates.builder()
                .add(filter.getArrivalCity(), flight.departureAirport.city::containsIgnoreCase)
                .add(filter.getDepartureCity(), flight.arrivalAirport.city::containsIgnoreCase)
                .add(filter.getReturnDate(), date ->
                        flight.departureDateTime.between(date.atStartOfDay(), date.plusDays(1).atStartOfDay()))
                .build();

        return flightRepository.findAll(predicate, pageable)
                .map(flightTicketMapper::map);
    }

    public Optional<FlightReadDto> findById(Long id){
        return flightRepository.findById(id)
                .map(flightReadMapper::map);
    }

    @Transactional
    public FlightReadDto create(FlightCreateEditDto flightDto){
        return Optional.ofNullable(flightDto)
                .map(flightCreateEditMapper::map)
                .map(flightRepository::save)
                .map(flightReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<FlightReadDto> update(Long id, FlightCreateEditDto flightDto){
        return flightRepository.findById(id)
                .map(entity -> flightCreateEditMapper.map(flightDto, entity))
                .map(flightRepository::saveAndFlush)
                .map(flightReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id){
        return flightRepository.findById(id)
                .map(entity -> {
                    flightRepository.delete(entity);
                    flightRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
