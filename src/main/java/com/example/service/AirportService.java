package com.example.service;

import com.example.database.Repository.AirportRepository;
import com.example.dto.*;
import com.example.mapper.AirportCreateEditMapper;
import com.example.mapper.AirportReadMapper;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.example.database.entity.QAirport.airport;

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
    public List<AirportReadDto> findAll(String search, String sort, String dir){
        QPredicates predicates = QPredicates.builder();

        if(StringUtils.isNotBlank(search)){
            predicates.add(search, airport.code::containsIgnoreCase);
            predicates.add(search, airport.name::containsIgnoreCase);
            predicates.add(search, airport.city::containsIgnoreCase);
            predicates.add(search, airport.country::containsIgnoreCase);
        }

        Set<String> allowed = Set.of("id", "code", "name", "city", "country", "timezone");

        if(!allowed.contains(sort)){
            sort = "id";
        }

        Sort sortQuery = dir.equals("asc") ? Sort.by(sort).ascending() : Sort.by(sort).descending();

        List<AirportReadDto> airportList = new ArrayList<>();
        airportRepository.findAll(predicates.buildOr(), sortQuery).forEach(entity ->{
            AirportReadDto dto = airportReadMapper.map(entity);
            airportList.add(dto);
        });

        return airportList;
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
