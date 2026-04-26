package com.example.http.rest;

import com.example.dto.PassengerDocumentDto;
import com.example.dto.PassengerSettingsDto;
import com.example.exception.FieldNotValidException;
import com.example.mapper.PassengerDocumentMapper;
import com.example.mapper.PassengerSettingsDtoMapper;
import com.example.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileRestController {
    private final PassengerService passengerService;
    private final PassengerDocumentMapper passengerDocumentMapper;
    private final PassengerSettingsDtoMapper passengerSettingsDtoMapper;

    @PatchMapping("/documents")
    public ResponseEntity<?> updateDocuments(@RequestBody @Validated PassengerDocumentDto dto,
                                             BindingResult bindingResult,
                                             @AuthenticationPrincipal User user){
        if(bindingResult.hasErrors()){
            throw new FieldNotValidException(bindingResult);
        }
        passengerService.getForEdit(user.getUsername())
                .map(createEditDto -> {
                    passengerDocumentMapper.map(dto, createEditDto);
                    passengerService.update(user.getUsername(), createEditDto);
                    return true;
                });
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/settings")
    public ResponseEntity<?> updateSettings(@RequestBody @Validated PassengerSettingsDto dto,
                                             BindingResult bindingResult,
                                             @AuthenticationPrincipal User user){
        if(bindingResult.hasErrors()){
            throw new FieldNotValidException(bindingResult);
        }
        passengerService.getForEdit(user.getUsername())
                .map(createEditDto -> {
                    passengerSettingsDtoMapper.map(dto, createEditDto);
                    passengerService.update(user.getUsername(), createEditDto);
                    return true;
                });
        return ResponseEntity.ok().build();
    }
}
