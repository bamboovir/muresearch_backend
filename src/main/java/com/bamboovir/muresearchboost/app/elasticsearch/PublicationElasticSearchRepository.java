package com.bamboovir.muresearchboost.app.elasticsearch;

import com.bamboovir.muresearchboost.model.Publication;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicationElasticSearchRepository extends ElasticsearchRepository<Publication, String> {
}
