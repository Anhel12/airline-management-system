package com.example.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    @GetMapping("/dashboard")
    public String dashboard(){
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
