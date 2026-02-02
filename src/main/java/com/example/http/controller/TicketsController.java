package com.example.http.controller;

import com.example.dto.FlightFilter;
import com.example.dto.FlightTicketDto;
import com.example.dto.PageResponse;
import com.example.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketsController {
    private final FlightService flightService;

    @GetMapping
    public String findAll(Model model, @ModelAttribute("filter")FlightFilter filter, @PageableDefault(value = 7) Pageable pageable){
        if(filter.getNumberOfPassenger() == null){
            filter.setNumberOfPassenger(1);
        }

        Page<FlightTicketDto> page;

        if(filter.getDepartureDate() != null && filter.getReturnDate() != null){
            Page<FlightTicketDto> departurePage = flightService.findAllForTicket(filter, pageable, true);
            Page<FlightTicketDto> returnPage = flightService.findAllForTicket(filter, pageable, false);
            
            List<FlightTicketDto> combinedContent = new ArrayList<>();
            combinedContent.addAll(departurePage.getContent());
            combinedContent.addAll(returnPage.getContent());

            page = new PageImpl<>(
                    combinedContent,
                    pageable,
                    departurePage.getTotalElements() + returnPage.getTotalElements()
            );
        }else if(filter.getDepartureDate() != null){
            page = flightService.findAllForTicket(filter, pageable, true);
        }else if(filter.getReturnDate() != null){
            page = flightService.findAllForTicket(filter, pageable, false);
        }else{
            page = flightService.findAllForTicket(pageable);
        }

        model.addAttribute("tickets", PageResponse.of(page));
        return "tickets/tickets";
    }
}
