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
import org.springframework.web.bind.annotation.*;

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
    public String airport(Model model,
                          @RequestParam(value = "search", required = false) String search,
                          @RequestParam(value = "sort", required = false, defaultValue = "1") String sort,
                          @RequestParam(value = "dir", required = false, defaultValue = "asc") String dir,
                          AirportListWrapper airportListWrapper){

        airportListWrapper.setAirportList(airportService.findAll(search, sort, dir));

        model.addAttribute("airportList", airportListWrapper);
        model.addAttribute("sort", sort);
        model.addAttribute("dir", dir);
        model.addAttribute("search", search);

        return "admin/airport";
    }

    @GetMapping("/booking")
    public String booking(Model model,
                          BookingListWrapper bookingListWrapper,
                          @RequestParam(value = "search", required = false) String search,
                          @RequestParam(value = "sort", required = false, defaultValue = "1") String sort,
                          @RequestParam(value = "dir", required = false, defaultValue = "asc") String dir){

        bookingListWrapper.setBookingList(bookingService.findAll(search, sort, dir));
        model.addAttribute("bookingList", bookingListWrapper);
        model.addAttribute("sort", sort);
        model.addAttribute("dir", dir);
        model.addAttribute("search", search);

        return "admin/booking";
    }

    @GetMapping("/flight")
    public String flight(Model model,
                         FlightListWrapper flightListWrapper,
                         @RequestParam(value = "search", required = false) String search,
                         @RequestParam(value = "sort", required = false, defaultValue = "1") String sort,
                         @RequestParam(value = "dir", required = false, defaultValue = "asc") String dir){

        flightListWrapper.setFlightList(flightService.findAll(search, sort, dir));
        model.addAttribute("flightList", flightListWrapper);
        model.addAttribute("sort", sort);
        model.addAttribute("dir", dir);
        model.addAttribute("search", search);

        return "admin/flight";
    }

    @GetMapping("/passenger")
    public String passenger(Model model,
                            PassengerListWrapper passengerListWrapper,
                            @RequestParam(value = "search", required = false) String search,
                            @RequestParam(value = "sort", required = false, defaultValue = "1") String sort,
                            @RequestParam(value = "dir", required = false, defaultValue = "asc") String dir){

        passengerListWrapper.setPassengerList(passengerService.findAll(search, sort, dir));
        model.addAttribute("passengerList", passengerListWrapper);
        model.addAttribute("sort", sort);
        model.addAttribute("dir", dir);
        model.addAttribute("search", search);

        return "admin/passenger";
    }
}
