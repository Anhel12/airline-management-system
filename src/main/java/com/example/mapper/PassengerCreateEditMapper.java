package com.example.mapper;


import com.example.database.entity.Passenger;
import com.example.dto.PassengerCreateEditDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class PassengerCreateEditMapper implements Mapper<PassengerCreateEditDto, Passenger> {

    @Override
    public Passenger map(PassengerCreateEditDto object) {
        Passenger passenger = new Passenger();
        copy(object, passenger);
        return passenger;
    }

    @Override
    public Passenger map(PassengerCreateEditDto fromObject, Passenger toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(PassengerCreateEditDto fromObject, Passenger toObject) {
        toObject.setEmail(fromObject.getEmail().toLowerCase());
        toObject.setPhoneNumber(fromObject.getPhoneNumber());
        toObject.setPassportNumber(fromObject.getPassportNumber());
        toObject.setBirthDate(fromObject.getBirthDate());
        toObject.setFirstName(fromObject.getFirstName());
        toObject.setMiddleName(fromObject.getMiddleName());
        toObject.setLastName(fromObject.getLastName());
        toObject.setSex(fromObject.getSex());
        toObject.setRole(fromObject.getRole());
        toObject.setPassword(fromObject.getPassword());
    }

    public PassengerCreateEditDto map(Passenger object){
        return PassengerCreateEditDto.builder()
                .phoneNumber(object.getPhoneNumber())
                .email(object.getEmail())
                .passportNumber(object.getPassportNumber())
                .birthDate(object.getBirthDate())
                .firstName(object.getFirstName())
                .middleName(object.getMiddleName())
                .lastName(object.getLastName())
                .sex(object.getSex())
                .role(object.getRole())
                .password(object.getPassword())
                .build();
    }
}
