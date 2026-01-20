package com.example.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {
    @GetMapping("/home")
    public String home(){
        return "home/home";
    }

    @GetMapping("/home_registration")
    public String home1(){
        return "home/home_registration";
    }
}
