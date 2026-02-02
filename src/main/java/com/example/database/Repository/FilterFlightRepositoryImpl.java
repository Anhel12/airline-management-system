package com.example.database.Repository;

import com.example.database.entity.Flight;
import com.example.dto.FlightFilter;
import com.example.dto.QPredicates;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.database.entity.QAirport.airport;
import static com.example.database.entity.QFlight.flight;

@RequiredArgsConstructor
public class FilterFlightRepositoryImpl implements FilterFlightRepository{
    private final EntityManager entityManager;
    @Override
    public List<Flight> findAllByFilterStartTicket(FlightFilter filter) {
        var predicate = QPredicates.builder()
                .add(filter.getDepartureCity(), flight.departureAirport.city::containsIgnoreCase)
                .add(filter.getArrivalCity(), flight.arrivalAirport.city::containsIgnoreCase)
                .add(filter.getDepartureDate(), date ->
                        flight.departureDateTime.between(date.atStartOfDay(), date.plusDays(1).atStartOfDay()))
                .build();

        return new JPAQuery<Flight>(entityManager)
                .select(flight)
                .from(flight)
                .where(predicate)
                .fetch();

    }

    @Override
    public List<Flight> findAllByFilterReturnTicket(FlightFilter filter) {
        var predicate = QPredicates.builder()
                .add(filter.getArrivalCity(), flight.departureAirport.city::containsIgnoreCase)
                .add(filter.getDepartureCity(), flight.arrivalAirport.city::containsIgnoreCase)
                .add(filter.getReturnDate(), date ->
                        flight.departureDateTime.between(date.atStartOfDay(), date.plusDays(1).atStartOfDay()))
                .build();

        return new JPAQuery<Flight>(entityManager)
                .select(flight)
                .from(flight)
                .where(predicate)
                .fetch();

    }
}
