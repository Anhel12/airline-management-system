package com.example.service;

import com.example.database.Repository.FlightRepository;
import com.example.dto.*;
import com.example.mapper.FlightCreateEditMapper;
import com.example.mapper.FlightReadMapper;
import com.example.mapper.FlightTicketMapper;
import com.querydsl.core.types.Predicate;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.example.database.entity.QBooking.booking;
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

    public List<FlightCreateEditDto> findAll(String search, String sort, String dir){
        QPredicates predicates = QPredicates.builder();

        if(StringUtils.isNotBlank(search)){
            predicates.add(search, flight.flightNumber::containsIgnoreCase);
            predicates.add(search, flight.departureDateTime.stringValue()::containsIgnoreCase);
            predicates.add(search, flight.arrivalDateTime.stringValue()::containsIgnoreCase);
            predicates.add(search, flight.departureAirport.code::containsIgnoreCase);
            predicates.add(search, flight.arrivalAirport.code::containsIgnoreCase);
            predicates.add(search, flight.departureAirport.name::containsIgnoreCase);
            predicates.add(search, flight.arrivalAirport.name::containsIgnoreCase);
        }

        Set<String> allowed = Set.of("id", "flightNumber", "departureDateTime", "arrivalDateTime", "departureAirport.code", "arrivalAirport.code", "departureAirport.name", "arrivalAirport.name");

        if(!allowed.contains(sort)){
            sort = "id";
        }

        Sort sortQuery = dir.equals("asc") ? Sort.by(sort).ascending() : Sort.by(sort).descending();

        List<FlightCreateEditDto> flightList = new ArrayList<>();
        flightRepository.findAll(predicates.buildOr(), sortQuery).forEach(entity ->{
            FlightCreateEditDto dto = flightCreateEditMapper.map(entity);
            flightList.add(dto);
        });

        return flightList;
    }

    public List<FlightTicketDto> findAllForTicket(){
        return flightRepository.findAll().stream()
                .map(flightTicketMapper::map)
                .toList();
    }

    public List<FlightTicketDto> findAllForTicket(FlightFilter filter){
        return flightRepository.findAllByFilter(filter).stream()
                .map(flightTicketMapper::map)
                .toList();
    }

    public Page<FlightTicketDto> findAllForTicket(FlightFilter filter, Pageable pageable){
        var predicates = QPredicates.builder();

        if(!filter.getDepartureCity().isBlank()){
            predicates.add(filter.getDepartureCity(), flight.departureAirport.city::containsIgnoreCase);
        }
        if(!filter.getArrivalCity().isBlank()){
            predicates.add(filter.getArrivalCity(), flight.arrivalAirport.city::containsIgnoreCase);
        }

        if(filter.getDepartureDate() != null){
            predicates.add(filter.getDepartureDate(), date ->
                    flight.departureDateTime.between(filter.getDepartureDate().atStartOfDay(), filter.getDepartureDate().plusDays(1).atStartOfDay()));
        }
        if(filter.getReturnDate() != null){
            predicates.add(filter.getReturnDate(), date ->
                    flight.departureDateTime.between(filter.getReturnDate().atStartOfDay(), filter.getReturnDate().plusDays(1).atStartOfDay()));
        }

        return flightRepository.findAll(predicates.build(), pageable)
                .map(flightTicketMapper::map);
    }

    public List<FlightCreateEditDto> findAllForCreateEdit(){
        return flightRepository.findAll().stream()
                .map(flightCreateEditMapper::map)
                .toList();
    }

    public Optional<FlightReadDto> findById(Long id){
        return flightRepository.findById(id)
                .map(flightReadMapper::map);
    }

    public Integer getCountAll(){
        return flightRepository.countAll();
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
