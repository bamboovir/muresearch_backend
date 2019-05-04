package com.bamboovir.muresearchboost.app.persistence;

import com.bamboovir.muresearchboost.model.People;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface PeopleRepository extends ReactiveMongoRepository<People, String> {
    Flux<People> findAllByName(String name);
}
