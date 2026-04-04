package com.example.service;

import com.example.database.Repository.BookingRepository;
import com.example.dto.AirportReadDto;
import com.example.dto.BookingCreateEditDto;
import com.example.dto.BookingReadDto;
import com.example.dto.QPredicates;
import com.example.mapper.BookingCreateEditMapper;
import com.example.mapper.BookingReadMapper;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.example.database.entity.QAirport.airport;
import static com.example.database.entity.QBooking.booking;

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

    public List<BookingCreateEditDto> findAll(String search, String sort, String dir){
        QPredicates predicates = QPredicates.builder();

        if(StringUtils.isNotBlank(search)){
            predicates.add(search, booking.bookingNumber::containsIgnoreCase);
            predicates.add(search, booking.flight.flightNumber::containsIgnoreCase);
            predicates.add(search, booking.bookingDate.stringValue()::containsIgnoreCase);
            predicates.add(search, booking.totalAmount.stringValue()::containsIgnoreCase);
            predicates.add(search, booking.status.stringValue()::containsIgnoreCase);
        }

        Set<String> allowed = Set.of("id", "bookingNumber", "flight.flightNumber", "bookingDate", "totalAmount", "status");

        if(!allowed.contains(sort)){
            sort = "id";
        }

        Sort sortQuery = dir.equals("asc") ? Sort.by(sort).ascending() : Sort.by(sort).descending();

        List<BookingCreateEditDto> bookingList = new ArrayList<>();
        bookingRepository.findAll(predicates.buildOr(), sortQuery).forEach(entity ->{
            BookingCreateEditDto dto = bookingCreateEditMapper.map(entity);
            bookingList.add(dto);
        });

        return bookingList;
    }

    public List<BookingCreateEditDto> findAllForCreateEdit(){
        return bookingRepository.findAll().stream()
                .map(bookingCreateEditMapper::map)
                .toList();
    }

    public Optional<BookingReadDto> findById(Long id){
        return bookingRepository.findById(id)
                .map(bookingReadMapper::map);
    }
    public Integer getCountAll(){
        return bookingRepository.countAll();
    }

    public Integer getSumByAllBookingAmount(){
        return bookingRepository.sumByAllBookingAmount();
    }

    public Optional<Integer> getSumByAllBookingAmountBetweenTwoDates(LocalDate firstDate, LocalDate secondDate){
        return bookingRepository.sumByAllBookingAmountBetweenTwoDates(firstDate, secondDate);
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
