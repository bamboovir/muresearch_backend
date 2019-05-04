package com.bamboovir.muresearchboost.app.persistence;

import com.bamboovir.muresearchboost.model.PeopleNameToPublications;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface PeopleNameToPublicationRepository extends ReactiveMongoRepository<PeopleNameToPublications, String> {
    Flux<PeopleNameToPublications> findAllByName(String name);
}
