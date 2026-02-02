package com.example.database.Repository;

import com.example.database.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long>,
        FilterFlightRepository, QuerydslPredicateExecutor<Flight> {

    public Optional<Flight> findByFlightNumberIgnoreCase(String flightNumber);
}
