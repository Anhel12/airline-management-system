package com.example.http.controller;

import com.example.database.entity.Role;
import com.example.dto.StatisticsDto;
import com.example.service.AirportService;
import com.example.service.BookingService;
import com.example.service.FlightService;
import com.example.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final PassengerService passengerService;
    private final BookingService bookingService;
    private final AirportService airportService;
    private final FlightService flightService;

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
    public String airport(){
        return "admin/airport";
    }

    @GetMapping("/booking")
    public String booking(){
        return "admin/booking";
    }

    @GetMapping("/flight")
    public String flight(){
        return "admin/flight";
    }

    @GetMapping("/passenger")
    public String passenger(){
        return "admin/passenger";
    }
}
