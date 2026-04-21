package com.example.http.rest;

import com.example.database.entity.Role;
import com.example.dto.SignInRequest;
import com.example.dto.SignUpRequest;
import com.example.exception.FieldNotValidException;
import com.example.service.AuthenticationService;
import com.example.service.PassengerService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;
    private final PassengerService passengerService;

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody @Validated SignUpRequest request,
                                    HttpServletResponse response,
                                    BindingResult bindingResult){

        if (passengerService.existByEmail(request.getEmail())){
            bindingResult.rejectValue("email", "error.email", "Почта уже занята");
        }

        if(bindingResult.hasErrors()){
            throw new FieldNotValidException(bindingResult);
        }


        var jwt = authenticationService.signUp(request).getToken();

        Cookie cookie = new Cookie("jwt", jwt);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24);

        response.addCookie(cookie);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody @Validated SignInRequest request,
                                    HttpServletResponse response,
                                    BindingResult bindingResult){

        if (!passengerService.existByEmail(request.getEmail())){
            bindingResult.rejectValue("email", "error.email", "Неверная почта или пароль");
        }

        if(bindingResult.hasErrors()){
            throw new FieldNotValidException(bindingResult);
        }

        String jwt = authenticationService.signIn(request).getToken();

        Cookie cookie = new Cookie("jwt", jwt);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24);

        response.addCookie(cookie);

        return ResponseEntity.ok().build();
    }
}
