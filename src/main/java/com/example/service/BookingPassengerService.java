package com.example.service;

import com.example.database.Repository.BookingPassengerRepository;
import com.example.dto.BookingPassengerDto;
import com.example.dto.BookingReadDto;
import com.example.mapper.BookingPassengerReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookingPassengerService {
    private final BookingPassengerRepository bookingPassengerRepository;
    private final BookingPassengerReadMapper bookingPassengerReadMapper;

    public List<BookingPassengerDto> findAllActiveBookingByPassengerEmail(String email){
        return bookingPassengerRepository.findFullActiveBookingByPassengerEmail(email).stream()
                .map(bookingPassengerReadMapper::map)
                .toList();
    }

    public List<BookingPassengerDto> findAllArchiveBookingByPassengerEmail(String email){
        return bookingPassengerRepository.findFullArchiveBookingByPassengerEmail(email).stream()
                .map(bookingPassengerReadMapper::map)
                .toList();
    }
}
