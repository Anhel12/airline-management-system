package com.example.database.Repository;

import com.example.database.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {
    @Query("select count(*) from Airport")
    public Integer countAll();
}
