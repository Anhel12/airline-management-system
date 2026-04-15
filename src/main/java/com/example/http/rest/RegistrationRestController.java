package com.example.http.rest;

import com.example.database.entity.Role;
import com.example.dto.PassengerCreateEditDto;
import com.example.service.PassengerService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/register")
public class RegistrationRestController {
    private final PassengerService passengerService;
    private final PasswordEncoder passwordEncoder;
    private final HttpServletRequest request;
    private final AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Validated PassengerCreateEditDto dto,
                                    BindingResult bindingResult){
        List<String> errors = new ArrayList<>();

        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
        }

        dto.setRole(Role.USER);

        if(passengerService.existByEmail(dto.getEmail().toLowerCase())){
            errors.add("Почта уже привязана к другому аккаунту");
        }

        if(!errors.isEmpty()) {
            return ResponseEntity.badRequest().body(errors);
        }
        String rawPassword = dto.getPassword();

        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        passengerService.create(dto);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(dto.getEmail(), rawPassword);

        Authentication authentication = authenticationManager.authenticate(authToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        request.getSession(true).setAttribute("SPRING_SECURITY_CONTEXT",
                SecurityContextHolder.getContext());

        return ResponseEntity.ok(Map.of("status", "ok"));
    }
}
