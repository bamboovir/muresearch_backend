package com.bamboovir.muresearchboost.app.elasticsearch;

import com.bamboovir.muresearchboost.model.People;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface PeopleElasticSearchRepository extends ElasticsearchRepository<People, String> {

}
