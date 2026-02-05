package com.example.http.controller;

import com.example.dto.FlightFilter;
import com.example.dto.FlightTicketDto;
import com.example.service.FlightService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanWrapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.charset.StandardCharsets;


@Controller
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketsController {
    private final FlightService flightService;

    @GetMapping
    public String findAll(Model model,
                          @ModelAttribute("filter") FlightFilter filter,
                          @PageableDefault(value = 7) Pageable pageable){

        if(filter.getNumberOfPassenger() == null){
            filter.setNumberOfPassenger(1);
        }


        Page<FlightTicketDto> page = flightService.findAllForTicket(filter, pageable);

        String url = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .replaceQueryParam("page")
                .build().toString();

        model.addAttribute("tickets", page);
        model.addAttribute("url", url);
        return "tickets/tickets";
    }

}
