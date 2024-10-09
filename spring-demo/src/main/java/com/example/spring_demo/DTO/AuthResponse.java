package com.example.spring_demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AuthResponse {
    private String message;
    private String roles; // Include user roles in the response
    private boolean success;

}