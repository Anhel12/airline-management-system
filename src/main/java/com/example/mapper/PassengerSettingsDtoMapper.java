package com.example.mapper;

import com.example.database.entity.Passenger;
import com.example.dto.PassengerCreateEditDto;
import com.example.dto.PassengerSettingsDto;
import org.springframework.stereotype.Component;

@Component
public class PassengerSettingsDtoMapper implements Mapper<Passenger, PassengerSettingsDto>{
    @Override
    public PassengerSettingsDto map(Passenger fromObject) {
        PassengerSettingsDto toObject = new PassengerSettingsDto();
        toObject.setEmail(fromObject.getEmail());
        toObject.setPhoneNumber(fromObject.getPhoneNumber());
        return toObject;
    }


    public PassengerCreateEditDto map(PassengerSettingsDto fromObject, PassengerCreateEditDto toObject) {
        toObject.setEmail(fromObject.getEmail());
        toObject.setPhoneNumber(fromObject.getPhoneNumber());
        return toObject;
    }
}
