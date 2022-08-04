package com.itvillage.book.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;

@Configuration
public class RouterConfig {
    @Bean
    public Validator javaxValidator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
