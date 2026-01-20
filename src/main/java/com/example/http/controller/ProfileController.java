package com.example.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    @GetMapping("/orders")
    public String activeOrders(){
        return "profile/active_orders";
    }

    @GetMapping("/archive")
    public String archiveOrders(){
        return "profile/archive_orders";
    }

    @GetMapping("/documents")
    public String documents(){
        return "profile/documents";
    }
}
