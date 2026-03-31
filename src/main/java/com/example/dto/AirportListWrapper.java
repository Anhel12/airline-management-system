package com.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class AirportListWrapper {
    private List<AirportReadDto> airportList = new ArrayList<>();
}
