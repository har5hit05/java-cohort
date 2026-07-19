package com.harshit.springSecurity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

//@RequiredArgsConstructor
//@NoArgsConstructor
//@Data
public class LoginResponseDto {
    private Long id;
    private String accessToken;
    private String refreshToken;
}
