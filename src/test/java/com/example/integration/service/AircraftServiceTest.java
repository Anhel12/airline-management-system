package com.example.integration.service;

import com.example.database.Repository.AircraftRepository;
import com.example.dto.AircraftReadDto;
import com.example.mapper.AircraftReadMapper;
import com.example.service.AircraftService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class AircraftServiceTest {
    @Autowired
    private AircraftService aircraftService;

    @Test
    @Transactional
    void checkFindAll(){
        List<AircraftReadDto> list = aircraftService.findAll();
        assertEquals(list.size(), 100);
    }

    @Test
    @Transactional
    void checkFindById(){
        Optional<AircraftReadDto> aircraftReadDto = aircraftService.findById(1L);
        assertFalse(aircraftReadDto.isEmpty());
    }

}
