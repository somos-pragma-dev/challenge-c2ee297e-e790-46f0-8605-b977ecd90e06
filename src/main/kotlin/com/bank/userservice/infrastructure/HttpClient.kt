package com.bank.userservice.infrastructure

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class HttpClient(builder: WebClient.Builder) {
    private val webClient: WebClient = builder.build()

    fun get(url: String): Mono<String> {
        return webClient.get()
           .uri(url)
           .retrieve()
           .bodyToMono(String::class.java)
    }
}