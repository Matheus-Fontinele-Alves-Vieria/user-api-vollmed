package com.mathvieira.user.api.dto;

import com.mathvieira.user.api.entity.User;

public record DataListingUser(String name, String email) {
    public DataListingUser(User user) {
        this(user.getName(),
             user.getEmail());
    }
}
