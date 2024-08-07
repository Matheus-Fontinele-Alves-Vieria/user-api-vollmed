package com.mathvieira.user.api.dto;

public record UserAuthenticationData(
    String email,
    String password) {}
