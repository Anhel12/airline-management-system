package com.example.service;

import com.example.database.Repository.AirportRepository;
import com.example.dto.*;
import com.example.mapper.AirportCreateEditMapper;
import com.example.mapper.AirportReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AirportService {
    private final AirportRepository airportRepository;
    private final AirportReadMapper airportReadMapper;
    private final AirportCreateEditMapper airportCreateEditMapper;

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

    @Transactional
    public Optional<AirportReadDto> update(Long id, AirportCreateEditDto airportDto){
        return airportRepository.findById(id)
                .map(entity -> airportCreateEditMapper.map(airportDto, entity))
                .map(airportRepository::saveAndFlush)
                .map(airportReadMapper::map);
    }
}
