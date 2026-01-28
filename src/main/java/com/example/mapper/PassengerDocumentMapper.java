package com.example.mapper;

import com.example.database.entity.Passenger;
import com.example.dto.PassengerCreateEditDto;
import com.example.dto.PassengerDocumentDto;
import org.hibernate.mapping.Map;
import org.springframework.stereotype.Component;

@Component
public class PassengerDocumentMapper implements Mapper<Passenger, PassengerDocumentDto> {
    @Override
    public PassengerDocumentDto map(Passenger object) {
        return PassengerDocumentDto.builder()
                .passportNumber(object.getPassportNumber())
                .birthDate(object.getBirthDate())
                .firstName(object.getFirstName())
                .middleName(object.getMiddleName())
                .lastName(object.getLastName())
                .sex(object.getSex())
                .build();
    }

    public PassengerCreateEditDto map(PassengerDocumentDto fromObject, PassengerCreateEditDto toObject){
        toObject.setPassportNumber(fromObject.passportNumber());
        toObject.setBirthDate(fromObject.birthDate());
        toObject.setFirstName(fromObject.firstName());
        toObject.setMiddleName(fromObject.middleName());
        toObject.setLastName(fromObject.lastName());
        toObject.setSex(fromObject.sex());
        return toObject;
    }
}
