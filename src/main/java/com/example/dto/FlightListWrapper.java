package com.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FlightListWrapper {
    private List<FlightCreateEditDto> flightList = new ArrayList<>();
}
