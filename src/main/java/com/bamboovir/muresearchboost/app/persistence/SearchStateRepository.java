package com.bamboovir.muresearchboost.app.persistence;

import com.bamboovir.muresearchboost.model.SearchState;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchStateRepository  extends ReactiveMongoRepository<SearchState, String> {
}
