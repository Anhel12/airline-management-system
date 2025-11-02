package com.example.service;

import com.example.database.Repository.AircraftRepository;
import com.example.dto.AircraftReadDto;
import com.example.mapper.AircraftReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AircraftService {
    private final AircraftRepository aircraftRepository;
    private final AircraftReadMapper aircraftReadMapper;

    public List<AircraftReadDto> findAll(){
        return aircraftRepository.findAll().stream()
                .map(aircraftReadMapper::map)
                .toList();
    }

    public Optional<AircraftReadDto> findById(Long id){
        return aircraftRepository.findById(id)
                .map(aircraftReadMapper::map);
    }
}
