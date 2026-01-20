package com.example.mapper;

import com.example.database.entity.BookingPassenger;
import com.example.database.entity.BookingSeat;
import com.example.database.entity.Passenger;
import com.example.dto.PassengerCreateEditDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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
        toObject.setEmail(fromObject.email());
        toObject.setPhoneNumber(fromObject.phoneNumber());
        toObject.setPassportNumber(fromObject.passportNumber());
        toObject.setBirthDate(fromObject.birthDate());
        toObject.setFirstName(fromObject.firstName());
        toObject.setMiddleName(fromObject.middleName());
        toObject.setLastName(fromObject.lastName());
        toObject.setSex(fromObject.sex());
        toObject.setRole(fromObject.role());
    }
}
