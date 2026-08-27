package com.bank.userservice.infrastructure

import io.github.resilience4j.circuitbreaker.CircuitBreaker
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CircuitBreakerConfig {

    @Bean
    fun circuitBreaker(): CircuitBreaker {
        return CircuitBreaker.of("user-service", CircuitBreakerConfig.ofDefaults())
    }
}