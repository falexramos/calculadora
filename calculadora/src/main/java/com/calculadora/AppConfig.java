package com.calculadora;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.corp.calculator.TracerImpl;

@Configuration
public class AppConfig {

    @Bean
    public TracerImpl tracer() {
        return new TracerImpl();
    }

}