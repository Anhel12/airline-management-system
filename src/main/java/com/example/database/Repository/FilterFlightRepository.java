package com.example.database.Repository;

import com.example.database.entity.Flight;
import com.example.dto.FlightFilter;

import java.util.List;

public interface FilterFlightRepository {
    List<Flight> findAllByFilter(FlightFilter filter);
}
