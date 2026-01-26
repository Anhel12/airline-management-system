package com.example.integration.repository;

import com.example.database.Repository.BookingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class BookingRepositoryTest {
    @Autowired
    private BookingRepository bookingRepository;

    @Test
    void checkFindById(){

    }
}
