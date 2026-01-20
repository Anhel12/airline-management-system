package com.example.mapper;

import com.example.database.Repository.PassengerRepository;
import com.example.database.entity.Passenger;
import com.example.dto.PassengerReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PassengerReadMapper implements Mapper<Passenger, PassengerReadDto>{
    @Override
    public PassengerReadDto map(Passenger object) {
        return PassengerReadDto.builder()
                .id(object.getId())
                .phoneNumber(object.getPhoneNumber())
                .email(object.getEmail())
                .passportNumber(object.getPassportNumber())
                .birthDate(object.getBirthDate())
                .firstName(object.getFirstName())
                .middleName(object.getMiddleName())
                .lastName(object.getLastName())
                .sex(object.getSex())
                .role(object.getRole())
                .build();
    }
}
