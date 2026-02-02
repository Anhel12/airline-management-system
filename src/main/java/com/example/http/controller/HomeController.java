package com.example.http.controller;

import com.example.dto.FlightFilter;
import com.example.dto.FlightTicketDto;
import com.example.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {
    private final FlightService flightService;

    @GetMapping("/home")
    public String home(Model model, FlightFilter filter){
        filter.setNumberOfPassenger(1);
        model.addAttribute("filter", filter);
        return "home/home";
    }

    @GetMapping("/home_registration")
    public String home1(){
        return "home/home_registration";
    }
}
