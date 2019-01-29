package com.bamboovir.muresearchboost.app.persistence;

import com.bamboovir.muresearchboost.model.People;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends ReactiveMongoRepository<People, String> {
}
