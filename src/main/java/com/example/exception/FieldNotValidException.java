package com.example.exception;

import lombok.Getter;
import org.springframework.validation.BindingResult;

@Getter
public class FieldNotValidException extends RuntimeException {

    private final BindingResult bindingResult;

    public FieldNotValidException(BindingResult bindingResult){
        this.bindingResult = bindingResult;
    }
}
