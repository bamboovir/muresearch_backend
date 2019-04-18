package com.bamboovir.muresearchboost.app.persistence;

import com.bamboovir.muresearchboost.model.TokenUser;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface TokenUserRepository extends ReactiveMongoRepository<TokenUser, String> {
    Mono<TokenUser> findByUsernameAndPassword(String username, String password);
    Mono<TokenUser> findByEmailAndPassword(String email, String password);
}
