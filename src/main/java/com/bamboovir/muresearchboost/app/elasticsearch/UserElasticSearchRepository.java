package com.bamboovir.muresearchboost.app.elasticsearch;

import com.bamboovir.muresearchboost.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface UserElasticSearchRepository extends ElasticsearchRepository<User, String> {
    Stream<User> findByFullnameLike(String name);
    Stream<User> findByEmailLike(String email);
    Stream<User> findByUsernameLike(String username);
    Page<User> findByUsernameLike(String username, Pageable pageable);

    /*
    @Query("{"bool" : {"must" : {"field" : {"name" : "?0"}}}}")
    Page<Book> findByName(String name,Pageable pageable);
}

    @Query("select u from User u")
Stream<User> findAllByCustomQueryAndStream();
     */
}
