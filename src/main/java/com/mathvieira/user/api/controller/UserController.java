package com.mathvieira.user.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mathvieira.user.api.config.UserEventPublisher;
import com.mathvieira.user.api.dto.AdminRegistrationData;
import com.mathvieira.user.api.dto.DataListingUser;
import com.mathvieira.user.api.dto.DoctorRegistrationData;
import com.mathvieira.user.api.dto.LoginResponseData;
import com.mathvieira.user.api.dto.PatientRegistrationData;
import com.mathvieira.user.api.dto.UserAuthenticationData;
import com.mathvieira.user.api.entity.User;
import com.mathvieira.user.api.entity.factory.UserFactory;
import com.mathvieira.user.api.repository.UserRepository;
import com.mathvieira.user.api.security.TokenService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserEventPublisher userEventPublisher;

    @PostMapping("/register/admin")
    @Transactional
    public ResponseEntity registerAdmin(@RequestBody AdminRegistrationData data) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User admin = UserFactory.createAdmin(data.name(),
                                             data.lastName(),
                                             data.email(),
                                             encryptedPassword,
                                             data.serialNumber());

        userRepository.save(admin);

        userEventPublisher.publishAdminCreated(admin);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register/doctor")
    @Transactional
    public ResponseEntity registerDoctor(@RequestBody DoctorRegistrationData data) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User doctor = UserFactory.createDoctor(data.name(),
                                               data.lastName(),
                                               data.email(),
                                               encryptedPassword,
                                               data.crm());

        userRepository.save(doctor);

        userEventPublisher.publishDoctorCreated(doctor);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register/patient")
    @Transactional
    public ResponseEntity registerPatient(@RequestBody PatientRegistrationData data) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User patient = UserFactory.createPatient(data.name(),
                                                 data.lastName(), 
                                                 data.email(),
                                                 encryptedPassword,
                                                 data.cpf(),
                                                 data.healthPlanNumber());

        userRepository.save(patient);

        userEventPublisher.publishPatientCreated(patient);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseData> login(@RequestBody @Valid UserAuthenticationData data) {    
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
            
        return ResponseEntity.ok(new LoginResponseData(token));
    }

    @GetMapping("")
    public Page<DataListingUser> list(@PageableDefault(size=10, sort={"name"}) Pageable pageable) {
        return userRepository.findAll(pageable).map(DataListingUser::new);
    }
}
