package com.bank.userservice.infrastructure

import com.bank.userservice.domain.User
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface UserRepository {
    fun save(user: User): Mono<User>
    fun findById(id: String): Mono<User>
    fun deleteById(id: String): Mono<Void>
}