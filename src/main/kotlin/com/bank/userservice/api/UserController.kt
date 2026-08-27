package com.bank.userservice.api

import com.bank.userservice.domain.User
import com.bank.userservice.infrastructure.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/users", produces = [MediaType.APPLICATION_JSON_VALUE])
class UserController(private val userRepository: UserRepository) {

    @PostMapping
    fun createUser(@RequestBody user: User): Mono<ResponseEntity<User>> {
        return userRepository.save(user)
           .map { ResponseEntity.status(HttpStatus.CREATED).body(it) }
    }

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: String): Mono<ResponseEntity<User>> {
        return userRepository.findById(id)
           .map { ResponseEntity.ok(it) }
           .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))
    }

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: String, @RequestBody user: User): Mono<ResponseEntity<User>> {
        return userRepository.findById(id)
           .flatMap { userRepository.save(user.copy(id = it.id)) }
           .map { ResponseEntity.ok(it) }
           .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: String): Mono<ResponseEntity<Void>> {
        return userRepository.findById(id)
           .flatMap { userRepository.deleteById(id) }
           .thenReturn(ResponseEntity.noContent().build())
           .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))
    }
}