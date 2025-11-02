package com.example.mapper;

import com.example.database.entity.SeatClass;
import com.example.dto.SeatClassReadDto;
import org.springframework.stereotype.Component;

@Component
public class SeatClassReadMapper implements Mapper<SeatClass, SeatClassReadDto> {
    @Override
    public SeatClassReadDto map(SeatClass object) {
        return SeatClassReadDto.builder()
                .id(object.getId())
                .fareClass(object.getFareClass())
                .build();
    }
}
