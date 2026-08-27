package com.bank.userservice

import com.bank.userservice.api.UserController
import com.bank.userservice.domain.User
import com.bank.userservice.infrastructure.UserRepository
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono

@WebFluxTest(UserController::class)
class UserControllerTest(
    @Autowired private val webTestClient: WebTestClient,
    @MockBean private val userRepository: UserRepository
) {

    @Test
    fun `create user returns 201 and user`() {
        val user = User(name = "John Doe", age = 30)
        whenever(userRepository.save(user)).thenReturn(Mono.just(user))

        webTestClient.post()
           .uri("/users")
           .contentType(MediaType.APPLICATION_JSON)
           .bodyValue(user)
           .exchange()
           .expectStatus().isCreated
           .expectBody(User::class.java).isEqualTo(user)
    }

    @Test
    fun `get user returns 200 and user`() {
        val user = User(id = "1", name = "John Doe", age = 30)
        whenever(userRepository.findById("1")).thenReturn(Mono.just(user))

        webTestClient.get()
           .uri("/users/1")
           .exchange()
           .expectStatus().isOk
           .expectBody(User::class.java).isEqualTo(user)
    }

    @Test
    fun `update user returns 200 and updated user`() {
        val user = User(id = "1", name = "John Doe", age = 30)
        whenever(userRepository.findById("1")).thenReturn(Mono.just(user))
        whenever(userRepository.save(user)).thenReturn(Mono.just(user))

        webTestClient.put()
           .uri("/users/1")
           .contentType(MediaType.APPLICATION_JSON)
           .bodyValue(user)
           .exchange()
           .expectStatus().isOk
           .expectBody(User::class.java).isEqualTo(user)
    }

    @Test
    fun `delete user returns 204`() {
        whenever(userRepository.findById("1")).thenReturn(Mono.just(User(id = "1", name = "John Doe", age = 30)))
        whenever(userRepository.deleteById("1")).thenReturn(Mono.empty())

        webTestClient.delete()
           .uri("/users/1")
           .exchange()
           .expectStatus().isNoContent
    }
}