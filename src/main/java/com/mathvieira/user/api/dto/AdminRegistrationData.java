package com.mathvieira.user.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AdminRegistrationData(
    @NotBlank
    String name,
    
    @NotBlank
    String lastName,
    
    @NotBlank
    @Email
    String email,
    
    @NotBlank
    String password,
    
    @NotBlank
    String serialNumber) {}
