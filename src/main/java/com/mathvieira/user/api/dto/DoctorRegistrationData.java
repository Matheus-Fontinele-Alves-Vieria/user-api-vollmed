package com.mathvieira.user.api.dto;

import jakarta.validation.constraints.NotBlank;

public record DoctorRegistrationData(
    @NotBlank
    String name,
    
    @NotBlank
    String lastName,
    
    @NotBlank
    String email,
    
    @NotBlank
    String password,
    
    @NotBlank
    String crm) {}
