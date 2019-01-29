package com.bamboovir.muresearchboost.app.persistence;

import com.bamboovir.muresearchboost.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {

}
