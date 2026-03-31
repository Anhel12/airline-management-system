package com.example.http.controller;

import com.example.database.entity.Role;
import com.example.dto.*;
import com.example.mapper.AirportCreateEditMapper;
import com.example.service.AirportService;
import com.example.service.BookingService;
import com.example.service.FlightService;
import com.example.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final PassengerService passengerService;
    private final BookingService bookingService;
    private final AirportService airportService;
    private final FlightService flightService;
    private final AirportCreateEditMapper airportCreateEditMapper;

    @GetMapping("/dashboard")
    public String dashboard(Model model){
        int passengers = passengerService.getCountAllByRole(Role.USER);
        int bookings = bookingService.getCountAll();
        int airports = airportService.getCountAll();
        int flights = flightService.getCountAll();

        int sumTotalAmounts = bookingService.getSumByAllBookingAmount();
        String sumTotalAmountWithFormat = String.format("%,d", sumTotalAmounts);

        LocalDate firstDate = LocalDate.now();
        int sumTotalAmountsInMonth = bookingService.getSumByAllBookingAmountBetweenTwoDates(firstDate,
                firstDate.minusMonths(1)).orElse(0);
        String sumTotalAmountsInMonthWithFormat = String.format("%,d", sumTotalAmountsInMonth);

        StatisticsDto statisticsDto = new StatisticsDto(passengers, bookings, airports, flights,
                sumTotalAmountWithFormat, sumTotalAmountsInMonthWithFormat);
        model.addAttribute("statistics", statisticsDto);
        return "admin/main";
    }

    @GetMapping("/airport")
    public String airport(Model model){
        AirportListWrapper airportListWrapper = new AirportListWrapper();
        airportListWrapper.setAirportList(airportService.findAll());

        model.addAttribute("airportList", airportListWrapper);

        return "admin/airport";
    }

    @PutMapping("/airport")
    public String airportUpdate(@ModelAttribute("airportList") AirportListWrapper airportListWrapper){

        airportListWrapper.getAirportList().forEach(airportReadDto -> {
                    AirportCreateEditDto dto = airportCreateEditMapper.map(airportReadDto);
                    airportService.update(dto.getId(), dto);
                });

        return "redirect:/admin/airport";
    }

    @GetMapping("/booking")
    public String booking(Model model){
        BookingListWrapper bookingListWrapper = new BookingListWrapper();
        bookingListWrapper.setBookingList(bookingService.findAllForCreateEdit());
        model.addAttribute("bookingList", bookingListWrapper);

        return "admin/booking";
    }

    @PutMapping("/booking")
    public String bookingUpdate(@ModelAttribute("bookingList") BookingListWrapper bookingListWrapper){
        bookingListWrapper.getBookingList().forEach(bookingDto -> bookingService.update(bookingDto.getId(), bookingDto));

        return "redirect:/admin/booking";
    }

    @GetMapping("/flight")
    public String flight(Model model){
        FlightListWrapper flightListWrapper = new FlightListWrapper();
        flightListWrapper.setFlightList(flightService.findAllForCreateEdit());
        model.addAttribute("flightList", flightListWrapper);

        return "admin/flight";
    }

    @PutMapping("/flight")
    public String flightUpdate(@ModelAttribute("flightList") FlightListWrapper flightListWrapper){
        flightListWrapper.getFlightList().forEach(flightDto -> flightService.update(flightDto.getId(), flightDto));

        return "redirect:/admin/flight";
    }

    @GetMapping("/passenger")
    public String passenger(Model model){
        PassengerListWrapper passengerListWrapper = new PassengerListWrapper();
        passengerListWrapper.setPassengerList(passengerService.findAllForCreatEdit());
        model.addAttribute("passengerList", passengerListWrapper);

        return "admin/passenger";
    }

    @PutMapping("/passenger")
    public String passengerUpdate(@ModelAttribute("passengerList") PassengerListWrapper passengerListWrapper){
        passengerListWrapper.getPassengerList().forEach(passengerDto -> passengerService.update(passengerDto.getId(), passengerDto));

        return "redirect:/admin/passenger";
    }
}
