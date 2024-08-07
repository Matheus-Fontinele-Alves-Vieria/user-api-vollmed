package com.mathvieira.user.api.entity;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("admin"),
    DOUTOR("doutor"),
    PACIENTE("paciente");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }
}
