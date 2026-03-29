package com.example.service;

import com.example.database.Repository.AirportRepository;
import com.example.dto.AircraftReadDto;
import com.example.dto.AirportReadDto;
import com.example.mapper.AirportReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirportService {
    private final AirportRepository airportRepository;
    private final AirportReadMapper airportReadMapper;

    public List<AirportReadDto> findAll(){
        return airportRepository.findAll().stream()
                .map(airportReadMapper::map)
                .toList();
    }

    public Optional<AirportReadDto> findById(Long id){
        return airportRepository.findById(id)
                .map(airportReadMapper::map);
    }

    public Integer getCountAll(){
        return airportRepository.countAll();
    }
}
