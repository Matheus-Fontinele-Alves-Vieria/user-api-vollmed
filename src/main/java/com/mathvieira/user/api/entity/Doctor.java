package com.mathvieira.user.api.entity;

import com.mathvieira.user.api.entity.interfaces.InterfaceDoctor;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@DiscriminatorValue("DOUTOR")
@Getter
@Setter
public class Doctor extends User implements InterfaceDoctor {
    private String crm;

    public Doctor(String name,
                  String lastName,
                  String email,
                  String password,
                  String crm) {

        super(name, lastName, email, password, UserRole.DOUTOR);
        this.crm = crm;
    }
}
