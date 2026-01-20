package com.example.http.controller;

import com.example.dto.LoginDto;
import com.example.dto.PassengerReadDto;
import com.example.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final PassengerService passengerService;

    @GetMapping("/login")
    public String login(){
        return "login/login";
    }

}
