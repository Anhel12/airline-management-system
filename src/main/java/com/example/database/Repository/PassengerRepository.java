package com.example.database.Repository;

import com.example.database.entity.Passenger;
import com.example.database.entity.Role;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long>, QuerydslPredicateExecutor<Passenger> {
    @QueryHints({
            @QueryHint(name = "org.hibernate.cacheable", value = "true")
    })
    public Optional<Passenger> findByEmail(String email);
    public Integer countAllByRoleIs(Role role);
}
