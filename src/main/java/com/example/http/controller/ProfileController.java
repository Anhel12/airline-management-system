package com.example.http.controller;

import com.example.service.BookingPassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final BookingPassengerService bookingPassengerService;

    @GetMapping("/orders")
    public String activeOrders(Model model,
                               @AuthenticationPrincipal User user){
        model.addAttribute("activeBookings", bookingPassengerService.findAllActiveBookingByPassengerEmail(user.getUsername()));
        return "profile/active_orders";
    }

    @GetMapping("/archive")
    public String archiveOrders(Model model,
                                @AuthenticationPrincipal User user){
        model.addAttribute("archiveBookings", bookingPassengerService.findAllArchiveBookingByPassengerEmail(user.getUsername()));
        return "profile/archive_orders";
    }

    @GetMapping("/documents")
    public String documents(){
        return "profile/documents";
    }
}
