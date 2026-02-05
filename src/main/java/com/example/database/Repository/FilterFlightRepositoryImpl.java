package com.example.database.Repository;

import com.example.database.entity.Airport;
import com.example.database.entity.Flight;
import com.example.dto.FlightFilter;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FilterFlightRepositoryImpl implements FilterFlightRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Flight> findAllByFilter(FlightFilter filter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Flight> cq = cb.createQuery(Flight.class);

        Root<Flight> flight = cq.from(Flight.class);

        List<Predicate> predicates = new ArrayList<>();

//        Приджойним сущность airport и Добавим условие - если было передано поле departureCity
        if(StringUtils.isNotBlank(filter.getDepartureCity())){
            Join<Flight, Airport> departureAirport =  flight.join("airport", JoinType.LEFT);
            predicates.add(cb.equal(departureAirport.get("city"),filter.getDepartureCity()));
        }
//        Приджойним сущность airport и Добавим условие - если было передано поле arrivalCity
        if (StringUtils.isNotBlank(filter.getArrivalCity())){
            Join<Flight, Airport> arrivalAirport = flight.join("airport", JoinType.LEFT);
            predicates.add(cb.equal(arrivalAirport.get("city"), filter.getArrivalCity()));
        }
//        Добавим условие - если было передано поле departureDate
        if(StringUtils.isNotBlank(filter.getDepartureDate().toString())){
            predicates.add(cb.between(flight.get("departure_date_time"), filter.getDepartureDate(), filter.getDepartureDate().plusDays(1)));
        }
//        Добавим условие - если было передано поле returnDate
        if(StringUtils.isNotBlank(filter.getReturnDate().toString())){
            predicates.add(cb.between(flight.get("departure_date_time"), filter.getReturnDate(), filter.getReturnDate().plusDays(1)));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(cq).getResultList();

    }
}
