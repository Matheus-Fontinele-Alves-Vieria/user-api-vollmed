package com.mathvieira.user.api.entity.factory;

import com.mathvieira.user.api.entity.*;

public class UserFactory {
    public static User createAdmin(String name,
                                   String lastName,
                                   String email,
                                   String password,
                                   String serialNumber) {
        
        return new Admin(name, lastName, email, password, serialNumber);
    }

    public static User createDoctor(String name,
                                   String lastName,
                                   String email,
                                   String password,
                                   String crm) {
        
        return new Doctor(name, lastName, email, password, crm);
    }

    public static User createPatient(String name,
                                   String lastName,
                                   String email,
                                   String password,
                                   String cpf,
                                   String healthPlanNumber) {
        
        return new Patient(name, lastName, email, password, cpf, healthPlanNumber);
    }
}
