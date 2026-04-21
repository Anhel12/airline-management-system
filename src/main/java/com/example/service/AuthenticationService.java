package com.example.service;

import com.example.database.entity.Passenger;
import com.example.database.entity.Role;
import com.example.dto.JwtAuthenticationResponse;
import com.example.dto.PassengerCreateEditDto;
import com.example.dto.SignInRequest;
import com.example.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PassengerService passengerService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public JwtAuthenticationResponse signUp(SignUpRequest request){
        var passenger = Passenger.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        passengerService.create(passenger);

        var jwt = jwtService.generateToken(passenger);
        return new JwtAuthenticationResponse(jwt);
    }

    public JwtAuthenticationResponse signIn(SignInRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));

        var passenger = passengerService
                .loadUserByUsername(request.getEmail());

        var jwt = jwtService.generateToken(passenger);
        return new JwtAuthenticationResponse(jwt);
    }
}
