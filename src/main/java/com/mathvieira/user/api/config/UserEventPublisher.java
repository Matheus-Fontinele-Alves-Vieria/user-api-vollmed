package com.mathvieira.user.api.config;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathvieira.user.api.entity.User;

@Service
public class UserEventPublisher {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TopicExchange userExchange;

    public void publishAdminCreated(User admin) {
        rabbitTemplate.convertAndSend(userExchange.getName(), "user.admin", admin);
    }

    public void publishDoctorCreated(User doctor) {
        rabbitTemplate.convertAndSend(userExchange.getName(), "user.doctor", doctor);
    }

    public void publishPatientCreated(User patient) {
        rabbitTemplate.convertAndSend(userExchange.getName(), "user.patient", patient);
    }
}
