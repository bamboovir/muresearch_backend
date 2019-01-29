package com.bamboovir.muresearchboost.app.persistence;

import com.bamboovir.muresearchboost.model.Publication;
import com.bamboovir.muresearchboost.model.Subject;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicationRepository  extends ReactiveMongoRepository<Publication, String> {
}
