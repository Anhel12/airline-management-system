package com.example.http.controller;

import com.example.database.entity.Role;
import com.example.dto.PassengerCreateEditDto;
import com.example.dto.PassengerReadDto;
import com.example.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/signup")
public class RegistrationController {
    private final PassengerService passengerService;

    @GetMapping
    public String registration(Model model, @ModelAttribute("passenger") PassengerCreateEditDto passengerCreateEditDto){
        model.addAttribute("passenger", passengerCreateEditDto);
        return "registration/signup";
    }

    @PostMapping
    public String create(@ModelAttribute("passenger") @Validated PassengerCreateEditDto passengerCreateEditDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("passenger", passengerCreateEditDto);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/signup";
        }

        passengerCreateEditDto.setRole(Role.USER);

        if(!passengerService.existByEmail(passengerCreateEditDto.getEmail())){
            passengerService.create(passengerCreateEditDto);
            return "redirect:/login";
        }

        redirectAttributes.addFlashAttribute("email", "Почта уже привязана к другому аккаунту");
        return "redirect:/signup";
    }
}
