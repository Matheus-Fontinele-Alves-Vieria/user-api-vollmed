package com.mathvieira.user.api.entity;

import com.mathvieira.user.api.entity.interfaces.InterfacePatient;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@DiscriminatorValue("PACIENTE")
@Getter
@Setter
public class Patient extends User implements InterfacePatient {
    private String cpf;
    private String healthPlanNumber;

    public Patient(String name,
                   String lastName,
                   String email,
                   String password,
                   String cpf,
                   String healthPlanNumber) {

        super(name, lastName, email, password, UserRole.PACIENTE);
        this.cpf = cpf;
        this.healthPlanNumber = healthPlanNumber;
    }
}
