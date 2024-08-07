package com.mathvieira.user.api.dto;

import jakarta.validation.constraints.NotBlank;

public record PatientRegistrationData(
    @NotBlank
    String name,
    
    @NotBlank
    String lastName,
    
    @NotBlank
    String email,
    
    @NotBlank
    String password,
    
    @NotBlank
    String cpf,
    
    String healthPlanNumber) {}
