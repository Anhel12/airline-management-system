package com.example.http.controller;

import com.example.service.BookingPassengerService;
import com.example.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final BookingPassengerService bookingPassengerService;
    private final PassengerService passengerService;

    @GetMapping("/orders")
    public String activeOrders(Model model,
                               @AuthenticationPrincipal User user){
        System.out.println(user);
        model.addAttribute("activeBookings", bookingPassengerService.findAllActiveBookingByPassengerEmail(user.getUsername()));
        return "site/pages/profile/active_orders";
    }

    @GetMapping("/archive")
    public String archiveOrders(Model model,
                                @AuthenticationPrincipal User user){
        model.addAttribute("archiveBookings", bookingPassengerService.findAllArchiveBookingByPassengerEmail(user.getUsername()));
        return "site/pages/profile/archive_orders";
    }

    @GetMapping("/documents")
    public String documents(Model model,
                            @AuthenticationPrincipal User user){
        if(!model.containsAttribute("document")){
            passengerService.getForPassengerDocument(user.getUsername())
                    .ifPresent(dto -> model.addAttribute("document", dto));
        }
        return "site/pages/profile/documents";
    }

    @GetMapping("/settings")
    public String settings(Model model,
                            @AuthenticationPrincipal User user){
        if(!model.containsAttribute("passenger")){
            passengerService.findByEmailForSettingsDto(user.getUsername())
                    .ifPresent(dto -> model.addAttribute("passenger", dto));
        }

        return "site/pages/profile/settings";
    }
}
