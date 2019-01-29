package com.bamboovir.muresearchboost.app.elasticsearch;

import com.bamboovir.muresearchboost.model.Subject;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectElasticSearchRepository extends ElasticsearchRepository<Subject, String> {
}
