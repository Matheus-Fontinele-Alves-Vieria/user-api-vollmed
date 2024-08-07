package com.mathvieira.user.api.entity;

import com.mathvieira.user.api.entity.interfaces.InterfaceAdmin;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@DiscriminatorValue("ADMIN")
@Getter
@Setter
public class Admin extends User implements InterfaceAdmin {
    private String serialNumber;

    public Admin(String name, 
                 String lastName, 
                 String email, 
                 String password, 
                 String serialNumber) {
        
        super(name, lastName, email, password, UserRole.ADMIN);
        this.serialNumber = serialNumber;
    }
}
