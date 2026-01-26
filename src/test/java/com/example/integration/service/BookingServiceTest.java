package com.example.integration.service;

import com.example.database.Repository.BookingRepository;
import com.example.database.entity.Booking;
import com.example.database.entity.BookingSeat;
import com.example.database.entity.Status;
import com.example.dto.BookingCreateEditDto;
import com.example.dto.BookingReadDto;
import com.example.dto.SeatReadDto;
import com.example.mapper.SeatReadMapper;
import com.example.service.BookingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")

public class BookingServiceTest {
    @Autowired
    private BookingService bookingService;

    @Test
    @Transactional(readOnly = true)
    void findAll(){
        List<BookingReadDto> list = bookingService.findAll();
        assertEquals(list.size(), 100);
    }

    @Test
    @Transactional(readOnly = true)
    void findById(){
        Optional<BookingReadDto> bookingReadDto = bookingService.findById(400L);
        assertTrue(bookingReadDto.isPresent());
        assertNotNull(bookingReadDto.get());

    }

    @Test
    void checkCreate(){
        BookingCreateEditDto bookingDto = BookingCreateEditDto.builder()
                .bookingNumber("BK-000101")
                .bookingDate(LocalDate.now())
                .flight_id(1L)
                .seat_id(List.of(1L))
                .totalAmount(10000)
                .status(Status.CONFIRMED)
                .build();
        BookingReadDto bookingReadDto = bookingService.create(bookingDto);

        assertEquals(bookingDto.bookingNumber(), bookingReadDto.bookingNumber());
    }

    @Test
    void checkUpdate(){
        Long id = 400L;
        Optional<BookingReadDto> OpBookingDto = bookingService.findById(id);
        assertNotNull(OpBookingDto, "BookingDto should be not null");

        BookingReadDto bookingDto = OpBookingDto.get();

        BookingCreateEditDto bookingEditDto = BookingCreateEditDto.builder()
                .bookingNumber(bookingDto.bookingNumber())
                .bookingDate(bookingDto.bookingDate())
                .flight_id(bookingDto.flight().id())
                .seat_id(bookingDto.seatList()
                        .stream()
                        .map(seatReadDto -> seatReadDto.id())
                        .toList())
                .totalAmount(bookingDto.totalAmount())
                .status(Status.COMPLETED)
                .build();

        BookingReadDto bookingDto2 = bookingService.update(333L, bookingEditDto).get();

        assertNotEquals(bookingDto, bookingDto2);
    }

    @Test
    void checkDelete(){
        boolean bool = bookingService.delete(409L);
        Optional<BookingReadDto> bookingDto = bookingService.findById(409L);
        assertTrue(bookingDto.isEmpty());
    }
}
