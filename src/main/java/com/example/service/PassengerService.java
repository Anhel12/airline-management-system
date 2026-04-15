package com.example.service;


import com.example.database.Repository.PassengerRepository;
import com.example.database.entity.Role;
import com.example.dto.*;
import com.example.mapper.*;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.example.database.entity.QFlight.flight;
import static com.example.database.entity.QPassenger.passenger;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PassengerService implements UserDetailsService {
    private final PassengerRepository passengerRepository;
    private final PassengerReadMapper passengerReadMapper;
    private final PassengerCreateEditMapper passengerCreateEditMapper;
    private final PassengerDocumentMapper passengerDocumentMapper;
    private final PassengerSettingsDtoMapper passengerSettingsDtoMapper;
    
    
    public List<PassengerReadDto> findAll(){
        return passengerRepository.findAll().stream()
                .map(passengerReadMapper::map)
                .toList();
    }

    public List<PassengerCreateEditDto> findAll(String search, String sort, String dir){
        QPredicates predicates = QPredicates.builder();

        if(StringUtils.isNotBlank(search)){
            predicates.add(search, passenger.phoneNumber::containsIgnoreCase);
            predicates.add(search, passenger.email::containsIgnoreCase);
            predicates.add(search, passenger.passportNumber::containsIgnoreCase);
            predicates.add(search, passenger.birthDate.stringValue()::containsIgnoreCase);
            predicates.add(search, passenger.firstName::containsIgnoreCase);
            predicates.add(search, passenger.middleName::containsIgnoreCase);
            predicates.add(search, passenger.lastName::containsIgnoreCase);
            predicates.add(search, passenger.sex.stringValue()::containsIgnoreCase);
            predicates.add(search, passenger.role.stringValue()::containsIgnoreCase);
            predicates.add(search, passenger.password::containsIgnoreCase);
        }

        Set<String> allowed = Set.of("id", "phoneNumber", "email", "passportNumber", "birthDate", "firstName", "middleName", "lastName", "sex", "role", "password");

        if(!allowed.contains(sort)){
            sort = "id";
        }

        Sort sortQuery = dir.equals("asc") ? Sort.by(sort).ascending() : Sort.by(sort).descending();

        List<PassengerCreateEditDto> passengerList = new ArrayList<>();
        passengerRepository.findAll(predicates.buildOr(), sortQuery).forEach(entity ->{
            PassengerCreateEditDto dto = passengerCreateEditMapper.map(entity);
            passengerList.add(dto);
        });

        return passengerList;
    }

    public List<PassengerCreateEditDto> findAllForCreatEdit(){
        return passengerRepository.findAll().stream()
                .map(passengerCreateEditMapper::map)
                .toList();
    }

    public Optional<PassengerReadDto> findById(Long id){
        return passengerRepository.findById(id)
                .map(passengerReadMapper::map);
    }
    public Optional<PassengerSettingsDto> findByEmailForSettingsDto(String email){
        return passengerRepository.findByEmail(email)
                .map(passengerSettingsDtoMapper::map);
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
    public Optional<PassengerReadDto> update(String email, PassengerCreateEditDto passengerDto){
        return passengerRepository.findByEmail(email)
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

    public Optional<PassengerDocumentDto> getForPassengerDocument(String email){
        return passengerRepository.findByEmail(email)
                .map(passengerDocumentMapper::map);
    }

    public Optional<PassengerCreateEditDto> getForEdit(String email){
        return passengerRepository.findByEmail(email)
                .map(passengerCreateEditMapper::map);
    }

    public Integer getCountAllByRole(Role role){
        return passengerRepository.countAllByRoleIs(role);
    }
}
