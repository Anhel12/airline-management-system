package com.example.http.controller;

import com.example.dto.PassengerCreateEditDto;
import com.example.dto.PassengerDocumentDto;
import com.example.mapper.PassengerDocumentMapper;
import com.example.service.BookingPassengerService;
import com.example.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
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
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final BookingPassengerService bookingPassengerService;
    private final PassengerService passengerService;
    private final PassengerDocumentMapper passengerDocumentMapper;

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
    public String documents(Model model,
                            @AuthenticationPrincipal User user){
        if(!model.containsAttribute("document")){
            passengerService.getForPassengerDocument(user.getUsername())
                    .ifPresent(dto -> model.addAttribute("document", dto));
        }
        return "profile/documents";
    }

    @PostMapping("/documents")
    public String update(Model model,
            @ModelAttribute("document") @Validated PassengerDocumentDto passengerDocumentDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes,
                         @AuthenticationPrincipal User user){

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("document", passengerDocumentDto);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/profile/documents";
        }

        passengerService.getForEdit(user.getUsername())
                        .map(createEditDto -> {
                            passengerDocumentMapper.map(passengerDocumentDto, createEditDto);
                            passengerService.update(user.getUsername(), createEditDto);
                            return true;
                        });

        return "redirect:/profile/documents";
    }
}
