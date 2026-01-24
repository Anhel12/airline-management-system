package com.example.service;


import com.example.database.Repository.PassengerRepository;
import com.example.dto.BookingCreateEditDto;
import com.example.dto.BookingReadDto;
import com.example.dto.PassengerCreateEditDto;
import com.example.dto.PassengerReadDto;
import com.example.mapper.BookingCreateEditMapper;
import com.example.mapper.PassengerCreateEditMapper;
import com.example.mapper.PassengerReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PassengerService implements UserDetailsService {
    private final PassengerRepository passengerRepository;
    private final PassengerReadMapper passengerReadMapper;
    private final PassengerCreateEditMapper passengerCreateEditMapper;
    
    
    public List<PassengerReadDto> findAll(){
        return passengerRepository.findAll().stream()
                .map(passengerReadMapper::map)
                .toList();
    }

    public Optional<PassengerReadDto> findById(Long id){
        return passengerRepository.findById(id)
                .map(passengerReadMapper::map);
    }

    public Optional<PassengerReadDto> findByEmail(String email){
        return passengerRepository.findByEmail(email)
                .map(passengerReadMapper::map);
    }


    @Transactional
    public PassengerReadDto create(PassengerCreateEditDto passengerDto){
        return Optional.ofNullable(passengerDto)
                .map(passengerCreateEditMapper::map)
                .map(passengerRepository::save)
                .map(passengerReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<PassengerReadDto> update(Long id, PassengerCreateEditDto passengerDto){
        return passengerRepository.findById(id)
                .map(entity -> passengerCreateEditMapper.map(passengerDto, entity))
                .map(passengerRepository::saveAndFlush)
                .map(passengerReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id){
        return passengerRepository.findById(id)
                .map(entity -> {
                    passengerRepository.delete(entity);
                    passengerRepository.flush();
                    return true;
                })
                .orElse(false);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return passengerRepository.findByEmail(email)
                .map(passenger -> new org.springframework.security.core.userdetails.User(
                        passenger.getEmail(),
                        passenger.getPassword(),
                        Collections.singleton(passenger.getRole())
                ))
                .orElseThrow(() -> new UsernameNotFoundException("failed to retrive user .." + email));
    }

    public boolean existByEmail(String email){
        return passengerRepository.findByEmail(email)
                .map(entity -> {
                     return true;
                })
                .orElse(false);
    }
}
