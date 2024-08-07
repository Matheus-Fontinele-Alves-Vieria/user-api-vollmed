package com.mathvieira.user.api.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {
    @Bean
    public TopicExchange userExchange() {
        return new TopicExchange("user.exchange");
    }

    @Bean
    public Queue adminQueue() {
        return new Queue("admin.queue");
    }

    @Bean
    public Queue doctorQueue() {
        return new Queue("doctor.queue");
    }

    @Bean
    public Queue patientQueue() {
        return new Queue("patient.queue");
    }

    @Bean
    public Binding adminBinding(@Qualifier("adminQueue") Queue adminQueue, TopicExchange userExchange) {
        return BindingBuilder.bind(adminQueue).to(userExchange).with("user.admin");
    }

    @Bean 
    public Binding doctorBinding(@Qualifier("doctorQueue") Queue doctorQueue, TopicExchange userExchange) {
        return BindingBuilder.bind(doctorQueue).to(userExchange).with("user.doctor");
    }

    @Bean 
    public Binding patientBinding(@Qualifier("patientQueue") Queue patienQueue, TopicExchange userExchange) {
        return BindingBuilder.bind(patienQueue).to(userExchange).with("user.patient");
    }
}
