package com.example.service;

import com.example.database.Repository.BookingRepository;
import com.example.dto.BookingCreateEditDto;
import com.example.dto.BookingReadDto;
import com.example.mapper.BookingCreateEditMapper;
import com.example.mapper.BookingReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookingService {
    private final BookingRepository bookingRepository;
    private final BookingReadMapper bookingReadMapper;
    private final BookingCreateEditMapper bookingCreateEditMapper;

    public List<BookingReadDto> findAll(){
        return bookingRepository.findAll().stream()
                .map(bookingReadMapper::map)
                .toList();
    }

    public Optional<BookingReadDto> findById(Long id){
        return bookingRepository.findById(id)
                .map(bookingReadMapper::map);
    }

    @Transactional
    public BookingReadDto create(BookingCreateEditDto bookingDto){
        return Optional.ofNullable(bookingDto)
                .map(bookingCreateEditMapper::map)
                .map(bookingRepository::save)
                .map(bookingReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<BookingReadDto> update(Long id, BookingCreateEditDto bookingDto){
        return bookingRepository.findById(id)
                .map(entity -> bookingCreateEditMapper.map(bookingDto, entity))
                .map(bookingRepository::saveAndFlush)
                .map(bookingReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id){
        return bookingRepository.findById(id)
                .map(entity -> {
                    bookingRepository.delete(entity);
                    bookingRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
