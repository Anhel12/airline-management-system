package com.example.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

@Setter
@Getter
public class LoginDto{
    private String username;
    private String password;
}
